package com.dev.hobby.outbox.application.query.command;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetOutboxCommand {
    private String aggregateId;
}
