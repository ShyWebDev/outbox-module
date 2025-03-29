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
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "outbox_event")
public class OutboxDocument {

    /** Mongo용 ID */
    @Id
    private String id;

    private String aggregateType;
    private String aggregateId;
    private String eventType;
    private String payload;
    private String status;
    private int retryCount;
    private String errorReason;
    private LocalDateTime createdAt;
    private LocalDateTime publishedAt;
}
