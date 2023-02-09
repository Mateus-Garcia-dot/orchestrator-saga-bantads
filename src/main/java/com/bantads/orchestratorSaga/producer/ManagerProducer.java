package com.bantads.orchestratorSaga.producer;

import com.bantads.orchestratorSaga.config.ManagerConfiguration;
import com.bantads.orchestratorSaga.model.ManagerModel;
import lombok.*;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RestController()
@RequestMapping("/manager")
public class ManagerProducer {

    @Autowired private RabbitTemplate rabbitTemplate;
    @Autowired private DirectExchange exchange;

    @PostMapping
    public void create(@RequestBody ManagerModel managerModel) {
        rabbitTemplate.convertAndSend(exchange.getName(), ManagerConfiguration.createManagerRouting, managerModel);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody ManagerModel managerModel) {
        managerModel.setId(id);
        rabbitTemplate.convertAndSend(exchange.getName(), ManagerConfiguration.updateManagerRouting, managerModel);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        rabbitTemplate.convertAndSend(exchange.getName(), ManagerConfiguration.deleteManagerRouting, id);
    }
}
