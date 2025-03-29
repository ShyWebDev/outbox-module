package com.dev.hobby.outbox.external.entity.repository;

import com.dev.hobby.outbox.domain.model.Outbox;
import com.dev.hobby.outbox.domain.repository.OutboxCmdRepository;
import com.dev.hobby.outbox.external.entity.repository.jpa.OutboxJpaRepository;
import com.dev.hobby.outbox.external.mapper.OutBoxExternalMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OutboxCmdRepositoryAdapter implements OutboxCmdRepository {

    private final OutboxJpaRepository outboxJpaRepository;

    @Override
    public void save(Outbox domain) {
        outboxJpaRepository.save(OutBoxExternalMapper.toEntity(domain));
    }
}