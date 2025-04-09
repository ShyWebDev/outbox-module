package com.dev.hobby.outbox.application.command.command;

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
    private String payload;
    private String callBackUrl;

    private LocalDateTime createAt;
}
