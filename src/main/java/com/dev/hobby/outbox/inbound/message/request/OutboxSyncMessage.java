package com.dev.hobby.outbox.inbound.message.request;

import java.time.LocalDateTime;

public record OutboxSyncMessage(
        String outboxId,

        String aggregateType,
        String aggregateId,
        String eventType,

        String status,
        Integer retryCount,
        LocalDateTime nextRetryAt,
        String lastError,
        String callbackUrl,

        LocalDateTime createdAt,
        LocalDateTime updatedAt
){}