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
public class ManagerConfiguration {

    public static final String createManagerRouting = "manager.create";
    public static final String updateManagerRouting = "manager.update";
    public static final String deleteManagerRouting = "manager.delete";
    public static final String patchManagerRouting = "manager.patch";

    @Bean
    public Queue createManagerQueue() {
        return new Queue(createManagerRouting, true);
    }

    @Bean
    public Queue updateManagerQueue() {
        return new Queue(updateManagerRouting, true);
    }

    @Bean
    public Queue deleteManagerQueue() {
        return new Queue(deleteManagerRouting, true);
    }

    @Bean
    public Queue patchManagerQueue() {
        return new Queue(patchManagerRouting, true);
    }

    @Bean
    Binding createManagerBinding(Queue createManagerQueue, DirectExchange exchange) {
        return BindingBuilder.bind(createManagerQueue).to(exchange).with(createManagerRouting);
    };

    @Bean
    Binding updateManagerBinding(Queue updateManagerQueue, DirectExchange exchange) {
        return BindingBuilder.bind(updateManagerQueue).to(exchange).with(updateManagerRouting);
    };

    @Bean
    Binding deleteManagerBinding(Queue deleteManagerQueue, DirectExchange exchange) {
        return BindingBuilder.bind(deleteManagerQueue).to(exchange).with(deleteManagerRouting);
    };

    @Bean
    Binding patchManagerBinding(Queue patchManagerQueue, DirectExchange exchange) {
        return BindingBuilder.bind(patchManagerQueue).to(exchange).with(patchManagerRouting);
    };

}
