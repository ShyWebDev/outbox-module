package com.dev.hobby.outbox.domain.service;

import com.dev.hobby.outbox.application.command.command.CreateOutboxCommand;
import com.dev.hobby.outbox.application.command.command.UpdateOutboxCommand;
import com.dev.hobby.outbox.domain.mapper.OutboxDomainMapper;
import com.dev.hobby.outbox.domain.model.OutboxStatus;
import com.dev.hobby.outbox.domain.repository.OutboxCmdRepository;
import com.dev.hobby.outbox.external.messaging.OutboxKafkaPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class OutboxDomainService {

    private final OutboxCmdRepository outboxCmdRepository; // 아웃박스 이벤트 데이터를 저장하는 리포지토리
    private final OutboxKafkaPublisher outboxKafkaPublisher; // 아웃박스 이벤트를 발행하는 퍼블리셔

    @Transactional
    public void createOutbox(CreateOutboxCommand createOutboxCommand , OutboxStatus outboxStatus) {
        if(ObjectUtils.isEmpty(createOutboxCommand))
            return;
        outboxCmdRepository.save(OutboxDomainMapper.byCreateOutboxCommand(createOutboxCommand, outboxStatus));
    }

    @Transactional
    public void updateOutboxFields(UpdateOutboxCommand updateOutboxCommand) {
        if(ObjectUtils.isEmpty(updateOutboxCommand))
            return;

        outboxCmdRepository.updateOutboxFields(
            updateOutboxCommand.getVersion(), updateOutboxCommand.getStatus(), updateOutboxCommand.getRetryCount(), updateOutboxCommand.getLastError()
                , updateOutboxCommand.getUpdatedAt(), updateOutboxCommand.getNextRetryAt(), updateOutboxCommand.getSyncedAt()
                , updateOutboxCommand.getOutboxId()
        );
    }
}
