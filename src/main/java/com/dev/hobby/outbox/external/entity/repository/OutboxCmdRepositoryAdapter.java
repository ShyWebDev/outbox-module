package com.dev.hobby.outbox.external.entity.repository;

import com.dev.hobby.outbox.application.event.OutboxCommittedEvent;
import com.dev.hobby.outbox.domain.model.Outbox;
import com.dev.hobby.outbox.domain.model.OutboxStatus;
import com.dev.hobby.outbox.domain.repository.OutboxCmdRepository;
import com.dev.hobby.outbox.external.entity.repository.jpa.OutboxJpaRepository;
import com.dev.hobby.outbox.external.mapper.OutBoxCmdMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Repository
@RequiredArgsConstructor
public class OutboxCmdRepositoryAdapter implements OutboxCmdRepository {

    private final OutboxJpaRepository outboxJpaRepository;
    private final ApplicationEventPublisher eventPublisher;  // 사용자 이벤트를 발행하는 퍼블리셔

    @Override
    @Transactional
    public void save(Outbox domain) {
        outboxJpaRepository.save(OutBoxCmdMapper.toEntity(domain));

        eventPublisher.publishEvent(OutboxCommittedEvent.builder()
                .outbox(domain)
                .build());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateOutboxFields(OutboxStatus status, Integer retryCount, String lastError, LocalDateTime nextRetryAt, String aggregateId) {
        outboxJpaRepository.updateOutboxFields(status, retryCount, lastError, nextRetryAt, aggregateId);
    }
}