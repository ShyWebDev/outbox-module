package com.dev.hobby.outbox.application.command.command;

import com.dev.hobby.outbox.common.AbstractOutboxBuilder;
import com.dev.hobby.outbox.common.AbstractOutboxFields;
import com.dev.hobby.outbox.domain.model.OutboxStatus;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
public class UpdateOutboxCommand {
    private String outboxId;

    private Integer version;
    private OutboxStatus status;
    private Integer retryCount;
    private String lastError;

    private LocalDateTime updatedAt;
    private LocalDateTime nextRetryAt;
    private LocalDateTime syncedAt;
}
