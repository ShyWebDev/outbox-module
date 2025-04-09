package com.dev.hobby.outbox.application.query.command;

import com.dev.hobby.outbox.domain.model.OutboxStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Builder
public class SyncOutboxCreateCmd {
    private String outboxId;

    private String aggregateType; // 도메인 타입 (예: ORDER)
    private String aggregateId; // Aggregate 식별자
    private String eventType; // 이벤트 종류 (예: ORDER_CREATED)

    private String status;
    private Integer retryCount; // 재시도 횟수
    private LocalDateTime nextRetryAt; // 다음 재시도 예정 시각
    private String lastError; // 마지막 에러 메시지

    private String callbackUrl;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
