package com.dev.hobby.outbox.external.mapper;

import com.dev.hobby.outbox.domain.model.Outbox;
import com.dev.hobby.outbox.external.document.OutboxDocument;
import lombok.experimental.UtilityClass;

/**
 * Domain → Kafka Event Object 변환
 * Event-Driven Architecture + DDD: 도메인 이벤트 → 외부 메시지 변환
 * Clean Architecture: 도메인은 메시징 시스템에 직접 의존하지 않음
 */
@UtilityClass
public class OutboxEventMapper {


    public static OutboxDocument toDocument(Outbox domain) {
        return OutboxDocument.builder()
                .outboxId(domain.getOutboxId())
                .aggregateType(domain.getAggregateType())
                .aggregateId(domain.getAggregateId())
                .eventType(domain.getEventType())
                .version(domain.getVersion())
                .status(domain.getStatus())
                .retryCount(domain.getRetryCount())
                .lastError(domain.getLastError())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .nextRetryAt(domain.getNextRetryAt())
                .syncedAt(domain.getSyncedAt())
                .build();
    }

    public static Outbox toDomain(OutboxDocument document) {
        return Outbox.builder()
                .outboxId(document.getOutboxId())
                .aggregateType(document.getAggregateType())
                .aggregateId(document.getAggregateId())
                .eventType(document.getEventType())
                .version(document.getVersion())
                .status(document.getStatus())
                .retryCount(document.getRetryCount())
                .lastError(document.getLastError())
                .createdAt(document.getCreatedAt())
                .updatedAt(document.getUpdatedAt())
                .nextRetryAt(document.getNextRetryAt())
                .syncedAt(document.getSyncedAt())
                .build();
    }



}