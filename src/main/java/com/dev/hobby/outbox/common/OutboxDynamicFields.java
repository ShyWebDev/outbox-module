package com.dev.hobby.outbox.common;

import com.dev.hobby.outbox.domain.model.OutboxStatus;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Embeddable
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OutboxDynamicFields {
    protected Integer version;
    protected OutboxStatus status;
    protected Integer retryCount;
    protected String lastError;

    protected LocalDateTime updatedAt;
    protected LocalDateTime nextRetryAt;
    protected LocalDateTime syncedAt;
}
