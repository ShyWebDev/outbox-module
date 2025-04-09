package com.dev.hobby.outbox.application.command.command;

import com.dev.hobby.outbox.domain.model.OutboxStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UpdateOutboxCommand {
    private String aggregateId;

    private OutboxStatus status;
    private Integer retryCount;
    private String lastError;

    private LocalDateTime nextRetryAt;
    private LocalDateTime syncedAt;
}
