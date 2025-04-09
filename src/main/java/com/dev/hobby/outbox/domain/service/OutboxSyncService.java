package com.dev.hobby.outbox.domain.service;

import com.dev.hobby.outbox.domain.model.Outbox;
import com.dev.hobby.outbox.domain.repository.OutboxCmdRepository;
import com.dev.hobby.outbox.domain.repository.OutboxQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OutboxSyncService {

    private final OutboxCmdRepository outboxCmdRepository;
    private final OutboxQueryRepository outboxQueryRepository;

    @Transactional
    public void syncOutbox() {
        List<Outbox> cmdDomainList = null;//outboxCmdRepository.findTop50BySyncedAtIsNullOrderByCreatedAt();
        for (Outbox cmdDomain : cmdDomainList) {
            try {
                processOutbox(cmdDomain);
            } catch (Exception e) {
                log.error("Failed to sync user. userDomain={}", cmdDomain, e);
            }
        }
    }

    public void processOutbox(Outbox cmdDomain) {
        // MongoDB 저장/갱신
        Optional<Outbox> existingQueryDomain = null;//outboxCmdRepository.findByOutboxId(cmdDomain.getOutboxId());

        if (existingQueryDomain.isPresent()) {
            Outbox queryDomain = existingQueryDomain.get();
            queryDomain.setStatus(cmdDomain.getStatus());
            queryDomain.setRetryCount(cmdDomain.getRetryCount());
            queryDomain.setLastError(cmdDomain.getLastError());
            //queryDomain.setUpdatedAt(cmdDomain.getUpdatedAt());
            queryDomain.setNextRetryAt(cmdDomain.getNextRetryAt());
            //queryDomain.setSyncedAt(cmdDomain.getSyncedAt());
            outboxQueryRepository.save(queryDomain);
        } else {
            outboxQueryRepository.save(cmdDomain);
        }

        //cmdDomain.setSyncedAt(LocalDateTime.now());
        outboxCmdRepository.save(cmdDomain);
    }
}