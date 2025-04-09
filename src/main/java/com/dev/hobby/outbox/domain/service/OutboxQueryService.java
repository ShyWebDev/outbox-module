package com.dev.hobby.outbox.domain.service;

import com.dev.hobby.outbox.application.query.command.GetOutboxCommand;
import com.dev.hobby.outbox.application.query.command.SyncOutboxCreateCmd;
import com.dev.hobby.outbox.domain.model.Outbox;
import com.dev.hobby.outbox.domain.repository.OutboxQueryRepository;
import com.dev.hobby.outbox.external.mapper.OutboxQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OutboxQueryService {

    private final OutboxQueryRepository outboxQueryRepository;

    public Outbox getOutbox(GetOutboxCommand getOutboxCommand){
        return outboxQueryRepository.findByAggregateId(getOutboxCommand.getAggregateId()).orElse(null);
    }

    public Outbox save(SyncOutboxCreateCmd syncOutboxCreateCmd) {
        return outboxQueryRepository.save(OutboxQueryMapper.toDomain(syncOutboxCreateCmd));
    }
}
