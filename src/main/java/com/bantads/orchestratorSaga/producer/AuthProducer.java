package com.bantads.orchestratorSaga.producer;

import com.bantads.orchestratorSaga.config.AuthConfiguration;
import com.bantads.orchestratorSaga.model.AuthModel;
import lombok.*;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

@Data
@AllArgsConstructor
@RestController()
@RequestMapping("/authentication")
public class AuthProducer {

    private RabbitTemplate rabbitTemplate;
    private DirectExchange exchange;

    @PostMapping
    public void create(@RequestBody AuthModel authModel) {
        rabbitTemplate.convertAndSend(exchange.getName(), AuthConfiguration.createAuthRouting, authModel);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable String id, @RequestBody AuthModel authModel) {
        authModel.setUuid(id);
        rabbitTemplate.convertAndSend(exchange.getName(), AuthConfiguration.updateAuthRouting, authModel);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        rabbitTemplate.convertAndSend(exchange.getName(), AuthConfiguration.deleteAuthRouting, id);
    }
}
