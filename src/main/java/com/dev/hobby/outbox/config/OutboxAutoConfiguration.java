package com.dev.hobby.outbox.config;

import com.dev.hobby.outbox.application.handler.OutboxHandler;
import com.dev.hobby.outbox.application.service.OutboxDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.dev.hobby.outbox")
public class OutboxAutoConfiguration {
}
