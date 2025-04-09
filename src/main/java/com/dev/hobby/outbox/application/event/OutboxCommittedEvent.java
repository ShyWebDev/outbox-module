package com.dev.hobby.outbox.application.event;

import com.dev.hobby.outbox.domain.model.Outbox;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OutboxCommittedEvent {
    private Outbox outbox;
}
