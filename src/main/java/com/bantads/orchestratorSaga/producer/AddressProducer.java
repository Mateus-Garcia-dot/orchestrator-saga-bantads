package com.bantads.orchestratorSaga.producer;

import com.bantads.orchestratorSaga.config.AddressConfiguration;
import com.bantads.orchestratorSaga.model.AddressModel;
import lombok.*;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

@Data
@AllArgsConstructor
@RestController()
@RequestMapping("/address")
public class AddressProducer {

    private RabbitTemplate rabbitTemplate;
    private DirectExchange exchange;

    @PostMapping
    public void create(@RequestBody AddressModel addressModel) {
        rabbitTemplate.convertAndSend(exchange.getName(), AddressConfiguration.createAddressRouting, addressModel);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable String id, @RequestBody AddressModel addressModel) {
        addressModel.setUuid(id);
        rabbitTemplate.convertAndSend(exchange.getName(), AddressConfiguration.updateAddressRouting, addressModel);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        rabbitTemplate.convertAndSend(exchange.getName(), AddressConfiguration.deleteAddressRouting, id);
    }

}