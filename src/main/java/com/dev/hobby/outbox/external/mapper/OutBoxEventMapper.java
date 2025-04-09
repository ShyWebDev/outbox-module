package com.dev.hobby.outbox.external.mapper;

import com.dev.hobby.outbox.application.event.OutboxCommittedEvent;
import com.dev.hobby.outbox.application.query.command.SyncOutboxCreateCmd;
import com.dev.hobby.outbox.domain.model.Outbox;
import com.dev.hobby.outbox.external.entity.OutboxEntity;
import com.dev.hobby.outbox.external.messaging.message.OutboxSyncEvent;
import com.dev.hobby.outbox.inbound.message.request.OutboxSyncMessage;
import lombok.experimental.UtilityClass;

/**
 * Mongo Document ↔ Domain 양방향 변환
 * Query 기반 Outbox 흐름
 * Clean Architecture: 기술에 대한 도메인 독립성 유지
 */
@UtilityClass
public class OutBoxEventMapper {

    public OutboxSyncEvent toOutboxSyncEvent(Outbox outbox) {
        return new OutboxSyncEvent(
                outbox.getOutboxId(),
                outbox.getAggregateType(),
                outbox.getAggregateId(),
                outbox.getEventType(),
                outbox.getStatus().name(),
                outbox.getRetryCount(),
                outbox.getNextRetryAt(),
                outbox.getLastError(),
                outbox.getCallbackUrl(),
                outbox.getCreatedAt(),
                outbox.getUpdatedAt()
        );
    }

    public SyncOutboxCreateCmd toSyncOutboxCreateCmd(OutboxSyncMessage message) {
        return SyncOutboxCreateCmd.builder()
                .outboxId(message.outboxId())
                .aggregateType(message.aggregateType())
                .aggregateId(message.aggregateId())
                .eventType(message.eventType())
                .status(message.status())
                .retryCount(message.retryCount())
                .nextRetryAt(message.nextRetryAt())
                .lastError(message.lastError())
                .callbackUrl(message.callbackUrl())
                .createdAt(message.createdAt())
                .updatedAt(message.updatedAt())
                .build();
    }

}