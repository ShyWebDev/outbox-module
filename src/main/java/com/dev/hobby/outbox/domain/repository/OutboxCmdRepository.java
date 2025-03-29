package com.dev.hobby.outbox.domain.repository;

import com.dev.hobby.outbox.domain.model.Outbox;

public interface OutboxCmdRepository {

    void save(Outbox domain);

}
