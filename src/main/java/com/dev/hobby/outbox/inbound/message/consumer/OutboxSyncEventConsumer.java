package com.dev.hobby.outbox.inbound.message.consumer;

import com.dev.hobby.outbox.application.query.handler.SyncOutboxHandler;
import com.dev.hobby.outbox.external.mapper.OutBoxEventMapper;
import com.dev.hobby.outbox.inbound.message.request.OutboxSyncMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OutboxSyncEventConsumer {

    private final ObjectMapper objectMapper;
    private final SyncOutboxHandler syncOutboxHandler;  // 아웃박스 이벤트 생성 핸들러

    /**
     * Kafka 메시지 수신 시 uniqueId 또는 타입에 따라 동기화 작업을 실행합니다.
     * 예시: 메시지 내용이 "OUTBOX:{uniqueId}" 또는 "USER:{userId}" 형태라고 가정
     */
    @KafkaListener(topics = "outbox.sync", groupId = "user-sync-group", containerFactory = "kafkaListenerContainerFactory")
    public void handleSyncUserCreatedEvent(String message) {
        log.info("Received message: {}", message);
        try{
            OutboxSyncMessage userSyncMessage = objectMapper.readValue(message, OutboxSyncMessage.class);
            var SyncOutboxCreateCmd = OutBoxEventMapper.toSyncOutboxCreateCmd(userSyncMessage);
            syncOutboxHandler.handle(SyncOutboxCreateCmd);
        } catch (Exception e) {
            log.error("Error processing message: {}", e.getMessage(), e);
        }
    }
}
