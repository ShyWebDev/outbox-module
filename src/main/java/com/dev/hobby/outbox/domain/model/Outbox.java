package com.dev.hobby.outbox.domain.model;

import lombok.*; // Lombok을 통한 보일러플레이트 제거
import java.time.LocalDateTime;
import java.util.List;

/**
 * 도메인 모델: Outbox
 * 하나의 비즈니스 이벤트를 나타내며,
 * 관련 메시지(OutboxDetail)들을 포함함
 */
@Getter // 모든 필드에 대한 Getter 생성
@Setter // 모든 필드에 대한 Setter 생성
@NoArgsConstructor // 기본 생성자 생성
@AllArgsConstructor // 전체 필드를 매개변수로 받는 생성자 생성
@Builder // 빌더 패턴 메서드 생성
public class Outbox {

    private Long outboxId;
    private String uniqueId; // 메시지 고유 식별자 (Kafka key 등)

    private String aggregateType; // 도메인 타입 (예: ORDER)
    private String aggregateId; // Aggregate 식별자

    private String eventType; // 이벤트 종류 (예: ORDER_CREATED)
    private Integer version; // 이벤트 버전

    private LocalDateTime occurredAt; // 이벤트 발생 시각

    private String status; // 상태 (PENDING, SENT, FAILED 등)
    private Integer retryCount; // 재시도 횟수
    private LocalDateTime nextRetryAt; // 다음 재시도 예정 시각
    private String lastError; // 마지막 에러 메시지

    private List<OutboxDetail> details; // 포함된 메시지 리스트 (1:N)

    public void addDetail(OutboxDetail detail) { // 메시지 추가 메서드
        this.details = List.of(detail); // 현재는 1건만 사용 (확장 가능)
    }

    public boolean isFailedAndRetryable() { // 재시도 가능 여부 확인
        return "FAILED".equalsIgnoreCase(status) && retryCount < 5;
    }
}