package com.bantads.orchestratorSaga.producer;

import com.bantads.orchestratorSaga.config.AccountConfiguration;
import com.bantads.orchestratorSaga.model.AccountModel;
import lombok.*;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RestController()
@RequestMapping("/account")
public class AccountProducer {

   @Autowired private RabbitTemplate rabbitTemplate;
   @Autowired private DirectExchange exchange;

   @PostMapping
    public void create(@RequestBody AccountModel accountModel) {
        rabbitTemplate.convertAndSend(exchange.getName(), AccountConfiguration.createAccountRouting, accountModel);
    }

   @PutMapping("/{id}")
    public void update(@PathVariable String id, @RequestBody AccountModel accountModel) {
        accountModel.setUuid(id);
        rabbitTemplate.convertAndSend(exchange.getName(), AccountConfiguration.updateAccountRouting, accountModel);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        rabbitTemplate.convertAndSend(exchange.getName(), AccountConfiguration.deleteAccountRouting, id);
    }

}
