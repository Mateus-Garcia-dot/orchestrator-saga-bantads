package com.bantads.orchestratorSaga.producer;

import com.bantads.orchestratorSaga.config.*;
import com.bantads.orchestratorSaga.model.*;
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
    public void register(@RequestBody ComposedCustomerModel composedCustomerModel) {

        String customerUuid = UUID.randomUUID().toString();
        String addressUuid = UUID.randomUUID().toString();
        String accountUuid = UUID.randomUUID().toString();

        AccountModel account = new AccountModel();
        CustomerModel customer = composedCustomerModel.getCustomer();
        AuthModel auth = composedCustomerModel.getAuthentication();
        AddressModel address = composedCustomerModel.getAddress();

        customer.setUuid(customerUuid);
        customer.setAddress(addressUuid);

        account.setUuid(accountUuid);

        account.setCustomer(customerUuid);
        account.setBalance(0.0);
        account.setLimitAmount(customer.getSalary()/2);

        auth.setCustomer(customerUuid);
        auth.setIsPending(true);

        address.setUuid(addressUuid);
        address.setCustomer(customerUuid);
        address.setCustomer(customerUuid);

        rabbitTemplate.convertAndSend(exchange.getName(), CustomerConfiguration.createCustomerRouting, customer);
        rabbitTemplate.convertAndSend(exchange.getName(), AddressConfiguration.createAddressRouting, address);
        rabbitTemplate.convertAndSend(exchange.getName(), AuthConfiguration.createAuthRouting, auth);
        rabbitTemplate.convertAndSend(exchange.getName(), AccountConfiguration.createAccountRouting, account);
    }

    @DeleteMapping("/manager/{id}")
    public void deleteManager(@PathVariable String id) {
        rabbitTemplate.convertAndSend(exchange.getName(), ManagerConfiguration.deleteManagerRouting, id);
        rabbitTemplate.convertAndSend(exchange.getName(), AccountConfiguration.deleteAccountManager, id);
    }

    @PatchMapping("/customer/{id}")
    public void patchCustomer(@PathVariable String id, @RequestBody ComposedCustomerModel composedCustomerModel) {
        composedCustomerModel.setUuid(id);
        AccountModel accountModel = new AccountModel();
        accountModel.setCustomer(id);
        accountModel.setLimitAmount(composedCustomerModel.getSalary()/2);

        composedCustomerModel.getAddress().setCustomer(composedCustomerModel.getCustomer().getUuid());
        composedCustomerModel.getAuthentication().setCustomer(composedCustomerModel.getCustomer().getUuid());
        composedCustomerModel.getAuthentication().setCustomer(composedCustomerModel.getCustomer().getUuid());

        rabbitTemplate.convertAndSend(exchange.getName(), CustomerConfiguration.patchCustomerRouting, composedCustomerModel.getCustomer());
        rabbitTemplate.convertAndSend(exchange.getName(), AddressConfiguration.patchAddressRouting, composedCustomerModel.getAddress());
        rabbitTemplate.convertAndSend(exchange.getName(), AuthConfiguration.patchAuthRouting, composedCustomerModel.getAuthentication());
        rabbitTemplate.convertAndSend(exchange.getName(), AccountConfiguration.patchAccountRouting, accountModel);
    }

}
