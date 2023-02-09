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
public class CustomerConfiguration {

     public static String createCustomerRouting = "customer.create";
     public static String updateCustomerRouting = "customer.update";
     public static String deleteCustomerRouting = "customer.delete";

    @Bean
    public Queue createCustomerQueue() {
        return new Queue(createCustomerRouting, true);
    }

    @Bean
    public Queue updateCustomerQueue() {
        return new Queue(updateCustomerRouting, true);
    }

    @Bean
    public Queue deleteCustomerQueue() {
        return new Queue(deleteCustomerRouting, true);
    }

    @Bean
    Binding createCustomerBinding(Queue createCustomerQueue, DirectExchange exchange) {
        return BindingBuilder.bind(createCustomerQueue).to(exchange).with(createCustomerRouting);
    }

    @Bean
    Binding updateCustomerBinding(Queue updateCustomerQueue, DirectExchange exchange) {
        return BindingBuilder.bind(updateCustomerQueue).to(exchange).with(updateCustomerRouting);
    }

    @Bean
    Binding deleteCustomerBinding(Queue deleteCustomerQueue, DirectExchange exchange) {
        return BindingBuilder.bind(deleteCustomerQueue).to(exchange).with(deleteCustomerRouting);
    }
}
