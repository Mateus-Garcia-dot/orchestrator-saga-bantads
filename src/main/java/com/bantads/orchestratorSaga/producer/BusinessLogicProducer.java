package com.bantads.orchestratorSaga.producer;

import com.bantads.orchestratorSaga.config.*;
import com.bantads.orchestratorSaga.model.AccountModel;
import com.bantads.orchestratorSaga.model.CustomerModel;
import com.bantads.orchestratorSaga.model.RegisterModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Data
@AllArgsConstructor
@RequestMapping("/bl")
public class BusinessLogicProducer {

    private RabbitTemplate rabbitTemplate;
    private DirectExchange exchange;

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

        registerModel.getAddress().setUuid(addressUuid);
        registerModel.getAccount().setCustomer(customerUuid);
        rabbitTemplate.convertAndSend(exchange.getName(), CustomerConfiguration.createCustomerRouting, registerModel.getCustomer());
        rabbitTemplate.convertAndSend(exchange.getName(), AddressConfiguration.createAddressRouting, registerModel.getAddress());
        rabbitTemplate.convertAndSend(exchange.getName(), AuthConfiguration.createAuthRouting, registerModel.getAuthentication());
        rabbitTemplate.convertAndSend(exchange.getName(), AccountConfiguration.createAccountRouting, registerModel.getAccount());
    }

    @PatchMapping("/customer/{id}")
    public void updateCustomer(@PathVariable String id, @RequestBody CustomerModel customerModel) {
        customerModel.setUuid(id);
        AccountModel accountModel = new AccountModel();
        accountModel.setCustomer(id);
        accountModel.setLimitAmount(customerModel.getSalary()/2);
        rabbitTemplate.convertAndSend(exchange.getName(), AccountConfiguration.patchAccountRouting, accountModel);
        rabbitTemplate.convertAndSend(exchange.getName(), CustomerConfiguration.patchCustomerRouting, customerModel);
    }

    @DeleteMapping("/manager/{id}")
    public void deleteManager(@PathVariable String id) {
        rabbitTemplate.convertAndSend(exchange.getName(), ManagerConfiguration.deleteManagerRouting, id);
        rabbitTemplate.convertAndSend(exchange.getName(), AccountConfiguration.deleteAccoutManager, id);
    }

}
