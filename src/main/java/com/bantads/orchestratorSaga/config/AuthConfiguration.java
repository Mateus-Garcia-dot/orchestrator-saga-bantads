package com.bantads.orchestratorSaga.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(RabbitMQConfiguration.class)
public class AuthConfiguration {

    public static final String createAuthRouting = "auth.create";
    public static final String updateAuthRouting = "auth.update";
    public static final String deleteAuthRouting = "auth.delete";
    public static final String patchAuthRouting = "auth.patch";

    @Bean
    public Queue createAuthQueue() {
        return new Queue(createAuthRouting, true);
    }

    @Bean
    public Queue updateAuthQueue() {
        return new Queue(updateAuthRouting, true);
    }

    @Bean
    public Queue deleteAuthQueue() {
        return new Queue(deleteAuthRouting, true);
    }

    @Bean
    public Queue patchAuthQueue() {
        return new Queue(patchAuthRouting, true);
    }

    @Bean
    Binding createAuthBinding(Queue createAuthQueue, DirectExchange exchange) {
        return BindingBuilder.bind(createAuthQueue).to(exchange).with(createAuthRouting);
    }

    @Bean
    Binding updateAuthBinding(Queue updateAuthQueue, DirectExchange exchange) {
        return BindingBuilder.bind(updateAuthQueue).to(exchange).with(updateAuthRouting);
    }

    @Bean
    Binding deleteAuthBinding(Queue deleteAuthQueue, DirectExchange exchange) {
        return BindingBuilder.bind(deleteAuthQueue).to(exchange).with(deleteAuthRouting);
    }

    @Bean
    Binding patchAuthBinding(Queue patchAuthQueue, DirectExchange exchange) {
        return BindingBuilder.bind(patchAuthQueue).to(exchange).with(patchAuthRouting);
    }

}
