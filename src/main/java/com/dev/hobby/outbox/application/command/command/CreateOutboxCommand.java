package com.dev.hobby.outbox.application.command.command;

import com.dev.hobby.outbox.common.OutboxFixedFields;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CreateOutboxCommand  {
    private String outboxId;
    private String aggregateType;
    private String aggregateId;
    private String eventType;

    private LocalDateTime createAt;
}
