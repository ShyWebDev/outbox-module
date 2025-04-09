package com.dev.hobby.outbox.external.mapper;

import com.dev.hobby.outbox.domain.model.Outbox;
import com.dev.hobby.outbox.external.entity.OutboxEntity;
import lombok.experimental.UtilityClass;

/**
 * Mongo Document ↔ Domain 양방향 변환
 * Query 기반 Outbox 흐름
 * Clean Architecture: 기술에 대한 도메인 독립성 유지
 */
@UtilityClass
public class OutBoxCmdMapper {

    public OutboxEntity toEntity(Outbox domain) {
        return OutboxEntity.builder()
                .outboxId(domain.getOutboxId())
                .aggregateType(domain.getAggregateType())
                .aggregateId(domain.getAggregateId())
                .eventType(domain.getEventType())
                .status(domain.getStatus())
                .retryCount(domain.getRetryCount())
                .nextRetryAt(domain.getNextRetryAt())
                .lastError(domain.getLastError())
                .build();
    }

    public Outbox toDomain(OutboxEntity entity) {
        return Outbox.builder()
                .outboxId(entity.getOutboxId())
                .aggregateType(entity.getAggregateType())
                .aggregateId(entity.getAggregateId())
                .eventType(entity.getEventType())
                .status(entity.getStatus())
                .retryCount(entity.getRetryCount())
                .nextRetryAt(entity.getNextRetryAt())
                .lastError(entity.getLastError())
                .build();
    }

}