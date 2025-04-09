package com.dev.hobby.outbox.external.mapper;

import com.dev.hobby.outbox.application.query.command.SyncOutboxCreateCmd;
import com.dev.hobby.outbox.domain.model.Outbox;
import com.dev.hobby.outbox.domain.model.OutboxStatus;
import com.dev.hobby.outbox.external.document.OutboxDocument;
import lombok.experimental.UtilityClass;

/**
 * Domain → Kafka Event Object 변환
 * Event-Driven Architecture + DDD: 도메인 이벤트 → 외부 메시지 변환
 * Clean Architecture: 도메인은 메시징 시스템에 직접 의존하지 않음
 */
@UtilityClass
public class OutboxQueryMapper {


    public static OutboxDocument toDocument(Outbox domain) {
        return OutboxDocument.builder()
                .outboxId(domain.getOutboxId())
                .aggregateType(domain.getAggregateType())
                .aggregateId(domain.getAggregateId())
                .eventType(domain.getEventType())
                .status(domain.getStatus().name())
                .retryCount(domain.getRetryCount())
                .nextRetryAt(domain.getNextRetryAt())
                .lastError(domain.getLastError())
                .callbackUrl(domain.getCallbackUrl())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }

    public static Outbox toDomain(OutboxDocument document) {
        return Outbox.builder()
                .outboxId(document.getOutboxId())
                .aggregateType(document.getAggregateType())
                .aggregateId(document.getAggregateId())
                .eventType(document.getEventType())
                .status(OutboxStatus.valueOf(document.getStatus()))
                .retryCount(document.getRetryCount())
                .nextRetryAt(document.getNextRetryAt())
                .lastError(document.getLastError())
                .callbackUrl(document.getCallbackUrl())
                .createdAt(document.getCreatedAt())
                .updatedAt(document.getUpdatedAt())
                .build();
    }

    public static Outbox toDomain(SyncOutboxCreateCmd command) {
        return Outbox.builder()
                .outboxId(command.getOutboxId())
                .aggregateType(command.getAggregateType())
                .aggregateId(command.getAggregateId())
                .eventType(command.getEventType())
                .status(OutboxStatus.valueOf(command.getStatus()))
                .retryCount(command.getRetryCount())
                .nextRetryAt(command.getNextRetryAt())
                .lastError(command.getLastError())
                .callbackUrl(command.getCallbackUrl())
                .createdAt(command.getCreatedAt())
                .updatedAt(command.getUpdatedAt())
                .build();
    }



}