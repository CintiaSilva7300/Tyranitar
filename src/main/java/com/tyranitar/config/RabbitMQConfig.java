package com.tyranitar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue FinancialTransactionQueue() {
        return new Queue("financial-transaction", true);
    }
}
