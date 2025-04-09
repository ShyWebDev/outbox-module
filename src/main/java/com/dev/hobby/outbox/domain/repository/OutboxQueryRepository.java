package com.dev.hobby.outbox.domain.repository;

import com.dev.hobby.outbox.domain.model.Outbox;

import java.util.Optional;

public interface OutboxQueryRepository {

    Outbox save(Outbox userDomain);
    Optional<Outbox> findByOutboxId(String outboxId);

    Optional<Outbox> findByAggregateId(String aggregateId);
}
