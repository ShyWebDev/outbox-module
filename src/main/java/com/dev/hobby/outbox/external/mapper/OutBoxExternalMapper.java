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
public class OutBoxExternalMapper {

    public OutboxEntity toEntity(Outbox domain) {
        return OutboxEntity.builder()
                .outboxId(domain.getOutboxId())
                .eventType(domain.getEventType())
                .status(domain.getStatus())
                .build();
    }

    public Outbox toDomain(OutboxEntity entity) {
        return Outbox.builder()
                .outboxId(entity.getOutboxId())
                .eventType(entity.getEventType())
                .status(entity.getStatus())
                .build();
    }

}