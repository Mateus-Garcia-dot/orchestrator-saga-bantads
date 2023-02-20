package com.bantads.orchestratorSaga.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(RabbitMQConfiguration.class)
@Configuration
public class AddressConfiguration {
    public static final String createAddressRouting = "address.create";
    public static final String updateAddressRouting = "address.update";
    public static final String deleteAddressRouting = "address.delete";
    public static final String patchAddressRouting = "address.patch";

    @Bean
    public Queue createAddressQueue() {
        return new Queue(createAddressRouting, true);
    }

    @Bean
    public Queue updateAddressQueue() {
        return new Queue(updateAddressRouting, true);
    }

    @Bean
    public Queue deleteAddressQueue() {
        return new Queue(deleteAddressRouting, true);
    }

    @Bean
    public Queue patchAddressQueue() {
        return new Queue(patchAddressRouting, true);
    }

    @Bean
    Binding createAddressBinding(Queue createAddressQueue, DirectExchange exchange) {
        return BindingBuilder.bind(createAddressQueue).to(exchange).with(createAddressRouting);
    }

    @Bean
    Binding updateAddressBinding(Queue updateAddressQueue, DirectExchange exchange) {
        return BindingBuilder.bind(updateAddressQueue).to(exchange).with(updateAddressRouting);
    }

    @Bean
    Binding deleteAddressBinding(Queue deleteAddressQueue, DirectExchange exchange) {
        return BindingBuilder.bind(deleteAddressQueue).to(exchange).with(deleteAddressRouting);
    }

    @Bean
    Binding patchAddressBinding(Queue patchAddressQueue, DirectExchange exchange) {
        return BindingBuilder.bind(patchAddressQueue).to(exchange).with(patchAddressRouting);
    }
}
