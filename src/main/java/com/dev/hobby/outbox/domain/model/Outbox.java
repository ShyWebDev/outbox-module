package com.dev.hobby.outbox.domain.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 도메인 모델: Outbox
 * 하나의 비즈니스 이벤트를 나타내며,
 * 관련 메시지(OutboxDetail)들을 포함함
 */
@Getter // 모든 필드에 대한 Getter 생성
@Setter // 모든 필드에 대한 Setter 생성
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder // 빌더 패턴 메서드 생성
public class Outbox {

    private String outboxId;

    private String aggregateType; // 도메인 타입 (예: ORDER)
    private String aggregateId; // Aggregate 식별자
    private String eventType; // 이벤트 종류 (예: ORDER_CREATED)

    private OutboxStatus status;
    private Integer retryCount; // 재시도 횟수
    private LocalDateTime nextRetryAt; // 다음 재시도 예정 시각
    private String lastError; // 마지막 에러 메시지

    private String callbackUrl;

    private LocalDateTime createdAt;

    @Setter
    private LocalDateTime updatedAt;

    private List<OutboxDetail> details; // 포함된 메시지 리스트 (1:N)
}