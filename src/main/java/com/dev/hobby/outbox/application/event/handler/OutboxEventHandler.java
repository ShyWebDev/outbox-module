package com.dev.hobby.outbox.application.event.handler;

import com.dev.hobby.outbox.application.event.OutboxCommittedEvent;
import com.dev.hobby.outbox.external.mapper.OutBoxEventMapper;
import com.dev.hobby.outbox.external.messaging.publisher.OutboxKafkaPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
@Slf4j
public class OutboxEventHandler {

    private final OutboxKafkaPublisher outboxKafkaPublisher;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleOutboxCommitted(OutboxCommittedEvent event) {
        try {
            outboxKafkaPublisher.publishOutboxSyncEvent(OutBoxEventMapper.toOutboxSyncEvent(event.getOutbox()));
        } catch (Exception e) {
            log.error("Error publishing user event: {}", e.getMessage(), e);
        }
    }


}
