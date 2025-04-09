package com.dev.hobby.outbox.external.document;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * OutboxDocument는 NoSQL 저장
 * NoSQL 기반 CQRS Query 용도로 사용됩니다.
 */
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Document(collection = "outbox")
public class OutboxDocument {

    /** Mongo용 ID */
    @Id
    private String id;

    private String outboxId;
    private String aggregateType;
    private String aggregateId;
    private String eventType;

    private String status;
    private Integer retryCount;
    private LocalDateTime nextRetryAt;
    private String lastError;

    private String callbackUrl;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
