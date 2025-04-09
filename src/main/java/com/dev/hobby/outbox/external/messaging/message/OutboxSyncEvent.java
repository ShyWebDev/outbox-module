package com.dev.hobby.outbox.external.messaging.message;

import java.time.LocalDateTime;

public record OutboxSyncEvent(
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