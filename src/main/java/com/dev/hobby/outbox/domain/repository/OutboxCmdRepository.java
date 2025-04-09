package com.dev.hobby.outbox.domain.repository;

import com.dev.hobby.outbox.domain.model.Outbox;
import com.dev.hobby.outbox.domain.model.OutboxStatus;

import java.time.LocalDateTime;

public interface OutboxCmdRepository {

    void save(Outbox domain);

    void updateOutboxFields(OutboxStatus status,
                            Integer retryCount,
                            String lastError,
                            LocalDateTime nextRetryAt,
                            String aggregateId);

}
