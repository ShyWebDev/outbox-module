package com.dev.hobby.outbox.domain.mapper;

import com.dev.hobby.outbox.application.command.command.CreateOutboxCommand;
import com.dev.hobby.outbox.application.command.command.UpdateOutboxCommand;
import com.dev.hobby.outbox.domain.model.Outbox;
import com.dev.hobby.outbox.domain.model.OutboxStatus;
import lombok.experimental.UtilityClass;

/**
 * Domain → Kafka Event Object 변환
 * Event-Driven Architecture + DDD: 도메인 이벤트 → 외부 메시지 변환
 * Clean Architecture: 도메인은 메시징 시스템에 직접 의존하지 않음
 */
@UtilityClass
public class OutboxDomainMapper {

    public static Outbox byCreateOutboxCommand(CreateOutboxCommand createOutboxCommand, OutboxStatus outboxStatus) {
        return Outbox.builder()
                .outboxId(createOutboxCommand.getOutboxId())
                .aggregateType(createOutboxCommand.getAggregateType())
                .aggregateId(createOutboxCommand.getAggregateId())
                .eventType(createOutboxCommand.getEventType())
                .status(outboxStatus)
                .retryCount(0)
                .createdAt(createOutboxCommand.getCreateAt())
                .build();
    }

}