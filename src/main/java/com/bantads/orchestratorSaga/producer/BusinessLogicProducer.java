package com.bantads.orchestratorSaga.producer;

import com.bantads.orchestratorSaga.config.AccountConfiguration;
import com.bantads.orchestratorSaga.config.AddressConfiguration;
import com.bantads.orchestratorSaga.config.AuthConfiguration;
import com.bantads.orchestratorSaga.config.CustomerConfiguration;
import com.bantads.orchestratorSaga.model.AccountModel;
import com.bantads.orchestratorSaga.model.CustomerModel;
import com.bantads.orchestratorSaga.model.RegisterModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Data
@AllArgsConstructor
@RequestMapping("/")
public class BusinessLogicProducer {

    private final RabbitTemplate rabbitTemplate;
    private final DirectExchange exchange;

    @PostMapping("/register")
    public void register(@RequestBody RegisterModel registerModel) {
        String customerUuid = UUID.randomUUID().toString();
        String addressUuid = UUID.randomUUID().toString();
        String accountUuid = UUID.randomUUID().toString();
        registerModel.setAccount(new AccountModel());

        registerModel.getCustomer().setUuid(customerUuid);
        registerModel.getCustomer().setAddress(addressUuid);

        registerModel.getAccount().setUuid(accountUuid);
        registerModel.getAccount().setCustomer(customerUuid);
        registerModel.getAccount().setBalance(0.0);
        registerModel.getAccount().setLimitAmount(registerModel.getCustomer().getSalary()/2);

        registerModel.getAuthentication().setAccount(accountUuid);
        registerModel.getAuthentication().setIsPending(true);
        System.out.println(registerModel.getAuthentication());

        registerModel.getAddress().setUuid(addressUuid);
        registerModel.getAccount().setCustomer(customerUuid);
        rabbitTemplate.convertAndSend(exchange.getName(), CustomerConfiguration.createCustomerRouting, registerModel.getCustomer());
        rabbitTemplate.convertAndSend(exchange.getName(), AddressConfiguration.createAddressRouting, registerModel.getAddress());
        rabbitTemplate.convertAndSend(exchange.getName(), AuthConfiguration.createAuthRouting, registerModel.getAuthentication());
        rabbitTemplate.convertAndSend(exchange.getName(), AccountConfiguration.createAccountRouting, registerModel.getAccount());
    }

}
