package com.dev.hobby.outbox.common;

import com.dev.hobby.outbox.domain.model.OutboxStatus;

import java.time.LocalDateTime;


public abstract class AbstractOutboxBuilder<T extends AbstractOutboxFields, B extends AbstractOutboxBuilder> {
    protected final T instance;

    protected AbstractOutboxBuilder(T instance) {
        this.instance = instance;
    }

    public B outboxId(String outboxId) {
        instance.setOutboxId(outboxId);
        return (B) this;
    }

    public B aggregateType(String aggregateType) {
        instance.setAggregateType(aggregateType);
        return (B) this;
    }

    public B aggregateId(String aggregateId) {
        instance.setAggregateId(aggregateId);
        return (B) this;
    }

    public B eventType(String eventType) {
        instance.setEventType(eventType);
        return (B) this;
    }

    public B version(Integer version) {
        instance.setVersion(version);
        return (B) this;
    }

    public B status(OutboxStatus status) {
        instance.setStatus(status);
        return (B) this;
    }

    public B retryCount(Integer retryCount) {
        instance.setRetryCount(retryCount);
        return (B) this;
    }

    public B lastError(String lastError) {
        instance.setLastError(lastError);
        return (B) this;
    }

    public B createdAt(LocalDateTime createdAt) {
        instance.setCreatedAt(createdAt);
        return (B) this;
    }

    public B updatedAt(LocalDateTime updatedAt) {
        instance.setUpdatedAt(updatedAt);
        return (B) this;
    }

    public B nextRetryAt(LocalDateTime nextRetryAt) {
        instance.setNextRetryAt(nextRetryAt);
        return (B) this;
    }

    public B syncedAt(LocalDateTime syncedAt) {
        instance.setSyncedAt(syncedAt);
        return (B) this;
    }

    public T build() {
        return instance;
    }
}
