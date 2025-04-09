package com.dev.hobby.outbox.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan(basePackages = "com.dev.hobby.outbox")
@EntityScan(basePackages ="com.dev.hobby.outbox.external.entity")
@EnableJpaRepositories(basePackages = "com.dev.hobby.outbox.external.entity.repository")
@EnableMongoRepositories( basePackages = "com.dev.hobby.outbox.external.document.repository")
public class OutboxAutoConfiguration {
}
