package com.bantads.orchestratorSaga.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(RabbitMQConfiguration.class)
public class AccountConfiguration {
    public static final String createAccountRouting = "account.create";
    public static final String updateAccountRouting = "account.update";
    public static final String deleteAccountRouting = "account.delete";
    public static final String patchAccountRouting = "account.patch.consumer";

    @Bean
    public Queue createAccountQueue() {
        return new Queue(createAccountRouting, true);
    }

    @Bean
    public Queue updateAccountQueue() {
        return new Queue(updateAccountRouting, true);
    }

    @Bean
    public Queue deleteAccountQueue() {
        return new Queue(deleteAccountRouting, true);
    }

    @Bean
    public Queue patchAccountQueue() {
        return new Queue(patchAccountRouting, true);
    }

    @Bean
    Binding createAccountBinding(Queue createAccountQueue, DirectExchange exchange) {
        return BindingBuilder.bind(createAccountQueue).to(exchange).with(createAccountRouting);
    }

    @Bean
    Binding updateAccountBinding(Queue updateAccountQueue, DirectExchange exchange) {
        return BindingBuilder.bind(updateAccountQueue).to(exchange).with(updateAccountRouting);
    }

    @Bean
    Binding deleteAccountBinding(Queue deleteAccountQueue, DirectExchange exchange) {
        return BindingBuilder.bind(deleteAccountQueue).to(exchange).with(deleteAccountRouting);
    }

    @Bean
    Binding patchAccountBinding(Queue patchAccountQueue, DirectExchange exchange) {
        return BindingBuilder.bind(patchAccountQueue).to(exchange).with(patchAccountRouting);
    }
}
