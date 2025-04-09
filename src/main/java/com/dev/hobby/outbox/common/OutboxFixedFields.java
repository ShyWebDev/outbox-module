package com.dev.hobby.outbox.common;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Embeddable
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OutboxFixedFields {
    private String outboxId;
    private String aggregateType;
    private String aggregateId;
    private String eventType;

    private String createAt;
}
