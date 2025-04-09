package com.dev.hobby.outbox.external.document.repository;

import com.dev.hobby.outbox.domain.model.Outbox;

/**
 * MongoDB 기반 아웃박스 이벤트 저장소입니다.
 */
public interface OutboxMongoRepository {
    void save(Outbox domain);
}