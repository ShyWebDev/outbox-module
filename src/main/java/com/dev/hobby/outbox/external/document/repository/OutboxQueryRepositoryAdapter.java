package com.dev.hobby.outbox.external.document.repository;

import com.dev.hobby.outbox.domain.model.Outbox;
import com.dev.hobby.outbox.domain.repository.OutboxQueryRepository;
import com.dev.hobby.outbox.external.document.repository.mongo.MongoOutboxRepository;
import com.dev.hobby.outbox.external.mapper.OutboxEventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OutboxQueryRepositoryAdapter implements OutboxQueryRepository {

    private final MongoOutboxRepository mongoOutboxRepository;

    @Override
    public void save(Outbox domain) {
        mongoOutboxRepository.save(OutboxEventMapper.toDocument(domain));
    }

    @Override
    public Optional<Outbox> findByOutboxId(String outboxId) {
        var optional = mongoOutboxRepository.findByOutboxId(outboxId);
        return optional.map(OutboxEventMapper::toDomain);
    }


}