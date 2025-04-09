package com.dev.hobby.outbox.external.messaging.publisher;

import com.dev.hobby.outbox.external.messaging.message.OutboxSyncEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * OutboxEvent를 Kafka로 전송
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class OutboxKafkaPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    private static final String OUTBOX_SYNC_TOPIC = "outbox.sync";

    public void publishOutboxSyncEvent(OutboxSyncEvent event) {
        try {
            // 이벤트 객체를 JSON 문자열로 변환
            String eventJson = objectMapper.writeValueAsString(event);
            kafkaTemplate.send(OUTBOX_SYNC_TOPIC, event.outboxId(), eventJson).get();
        } catch (Exception e) {
            log.error("Error publishing user event: {}", e.getMessage(), e);
        }
    }
}