package com.dev.hobby.outbox.scheduler;

import com.dev.hobby.outbox.domain.service.OutboxSyncService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OutboxSyncScheduler {

    private final OutboxSyncService outboxSyncService;

    @Scheduled(fixedDelay = 3000)
    public void sync() {
        outboxSyncService.syncOutbox();
    }
}
