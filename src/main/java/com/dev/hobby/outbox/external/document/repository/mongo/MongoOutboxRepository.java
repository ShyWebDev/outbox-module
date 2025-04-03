package com.dev.hobby.outbox.external.document.repository.mongo;

import com.dev.hobby.outbox.external.document.OutboxDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface MongoOutboxRepository extends MongoRepository<OutboxDocument, String> {

    Optional<OutboxDocument> findByOutboxId(String outboxId);
}
