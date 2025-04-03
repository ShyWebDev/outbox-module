package com.dev.hobby.outbox.application.command.handler;

import com.dev.hobby.outbox.application.command.command.CreateOutboxCommand;
import com.dev.hobby.outbox.application.command.command.UpdateOutboxCommand;
import com.dev.hobby.outbox.domain.model.OutboxStatus;
import com.dev.hobby.outbox.domain.service.OutboxDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;

/**
 * OutboxSyncUseCase는 발행되지 않은 아웃박스 이벤트를 가져와 퍼블리시하는 역할을 수행합니다.
 * 애플리케이션 계층에 위치하며, 도메인 유스케이스를 실행하는 'UseCase'의 역할을 담당합니다.
 * 클린 아키텍처에서의 흐름 제어자(Interactor)입니다.
 */
@Component
@RequiredArgsConstructor
public class SaveOutboxHandler {

    private final OutboxDomainService outboxDomainService;

    public void CreateHandler(CreateOutboxCommand createOutboxCommand) {
        outboxDomainService.createOutbox(createOutboxCommand, OutboxStatus.RECEIVED);
    }

    public void processProcessingHandler(String outboxId) {
        UpdateHandler(UpdateOutboxCommand.builder()
                .outboxId(outboxId)
                .status(OutboxStatus.PROCESSING)
                .build());
    }

    public void processSuccessHandler(String outboxId) {
        UpdateHandler(UpdateOutboxCommand.builder()
                .outboxId(outboxId)
                .version(0)
                .status(OutboxStatus.SUCCESS)
                .retryCount(0)
                .lastError(null)
                .nextRetryAt(null)
                .build());
    }

    public void processFailedHandler(String outboxId, String lastError) {
        UpdateHandler(UpdateOutboxCommand.builder()
                .outboxId(outboxId)
                .version(0)
                .status(OutboxStatus.SUCCESS)
                .retryCount(0)
                .lastError(lastError)
                .nextRetryAt(LocalDateTime.now().plusMinutes(1))
                .build());
    }

    public void processPublishingHandler(String outboxId) {
        UpdateHandler(UpdateOutboxCommand.builder()
                .outboxId(outboxId)
                .status(OutboxStatus.PUBLISHING)
                .retryCount(0)
                .build());
    }

    public void processPublishedHandler(String outboxId) {
        UpdateHandler(UpdateOutboxCommand.builder()
                .outboxId(outboxId)
                .status(OutboxStatus.PUBLISHED)
                .retryCount(0)
                .build());
    }

    private void UpdateHandler(UpdateOutboxCommand updateOutboxCommand) {
        outboxDomainService.updateOutboxFields(updateOutboxCommand);
    }

}
