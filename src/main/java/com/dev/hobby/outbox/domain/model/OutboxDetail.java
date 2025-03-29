package com.dev.hobby.outbox.domain.model;

import lombok.*; // Lombok으로 기본 메서드 자동 생성
import java.time.LocalDateTime;

/**
 * 도메인 모델: OutboxDetail
 * 실제 전송될 Kafka 메시지 한 건을 표현함
 */
@Getter // Getter 생성
@Setter // Setter 생성
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 전체 생성자
@Builder // 빌더 생성
public class OutboxDetail {

    private Long id; // 메시지 고유 ID (AUTO_INCREMENT)

    private String topic; // Kafka 토픽
    private String payload; // 메시지 본문 (JSON 문자열)
    private String headers; // Kafka 헤더 정보 (JSON 문자열)

    private Long sequenceNumber; // 순서 보장용 시퀀스 번호

    private String status; // 상태 (PENDING, SENT, FAILED)
    private Integer retryCount; // 재시도 횟수
    private LocalDateTime nextRetryAt; // 다음 재시도 시각
    private LocalDateTime processedAt; // 전송 완료 시각
    private String lastError; // 마지막 에러 메시지

    public boolean isPending() { // 대기 상태 여부 확인
        return "PENDING".equalsIgnoreCase(status);
    }

    public boolean isReadyToRetry(LocalDateTime now) { // 재시도 조건 확인
        return "FAILED".equalsIgnoreCase(status) &&
                nextRetryAt != null &&
                now.isAfter(nextRetryAt);
    }
}