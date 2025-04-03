package com.dev.hobby.outbox.common;

import com.dev.hobby.outbox.domain.model.OutboxStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class AbstractOutboxFields {
    protected String outboxId;
    protected String aggregateType;
    protected String aggregateId;
    protected String eventType;
    protected Integer version;
    protected OutboxStatus status;
    protected Integer retryCount;
    protected String lastError;

    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
    protected LocalDateTime nextRetryAt;
    protected LocalDateTime syncedAt;
}
