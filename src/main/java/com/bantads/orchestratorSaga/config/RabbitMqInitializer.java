package com.bantads.orchestratorSaga.config;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
public class RabbitMqInitializer {

    private final AmqpAdmin amqpAdmin;

    @PostConstruct
    public void createQueues() {
        amqpAdmin.declareQueue(new Queue(AccountConfiguration.createAccountRouting, true));
        amqpAdmin.declareQueue(new Queue(AccountConfiguration.updateAccountRouting, true));
        amqpAdmin.declareQueue(new Queue(AccountConfiguration.deleteAccountRouting, true));
        amqpAdmin.declareQueue(new Queue(ManagerConfiguration.createManagerRouting, true));
        amqpAdmin.declareQueue(new Queue(ManagerConfiguration.updateManagerRouting, true));
        amqpAdmin.declareQueue(new Queue(ManagerConfiguration.deleteManagerRouting, true));
        amqpAdmin.declareQueue(new Queue(CustomerConfiguration.createCustomerRouting, true));
        amqpAdmin.declareQueue(new Queue(CustomerConfiguration.updateCustomerRouting, true));
        amqpAdmin.declareQueue(new Queue(CustomerConfiguration.deleteCustomerRouting, true));
        amqpAdmin.declareQueue(new Queue(AuthConfiguration.createAuthRouting, true));
        amqpAdmin.declareQueue(new Queue(AuthConfiguration.updateAuthRouting, true));
        amqpAdmin.declareQueue(new Queue(AuthConfiguration.deleteAuthRouting, true));
    }
}
