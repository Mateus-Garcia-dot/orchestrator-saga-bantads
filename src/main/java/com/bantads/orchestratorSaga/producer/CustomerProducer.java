package com.bantads.orchestratorSaga.producer;

import com.bantads.orchestratorSaga.config.CustomerConfiguration;
import com.bantads.orchestratorSaga.model.CustomerModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RestController()
@RequestMapping("/custumer")
public class CustomerProducer {


    @Autowired private RabbitTemplate rabbitTemplate;
    @Autowired private DirectExchange exchange;

    @PostMapping
    public void create(@RequestBody CustomerModel customerModel) {
        rabbitTemplate.convertAndSend(exchange.getName(), CustomerConfiguration.createCustomerRouting, customerModel);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody CustomerModel customerModel) {
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        rabbitTemplate.convertAndSend(exchange.getName(), CustomerConfiguration.deleteCustomerRouting, id);
    }
}
