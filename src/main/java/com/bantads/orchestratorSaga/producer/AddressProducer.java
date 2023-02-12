package com.bantads.orchestratorSaga.producer;

import com.bantads.orchestratorSaga.config.AddressConfiguration;
import com.bantads.orchestratorSaga.model.AddressModel;
import lombok.*;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RestController()
@RequestMapping("/addresss")
public class AddressProducer {

    @Autowired private RabbitTemplate rabbitTemplate;
    @Autowired private DirectExchange exchange;

    @PostMapping
    public void create(@RequestBody AddressModel addressModel) {
        rabbitTemplate.convertAndSend(exchange.getName(), AddressConfiguration.createAddressRouting, addressModel);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody AddressModel addressModel) {
        addressModel.setId(id);
        rabbitTemplate.convertAndSend(exchange.getName(), AddressConfiguration.updateAddressRouting, addressModel);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        rabbitTemplate.convertAndSend(exchange.getName(), AddressConfiguration.deleteAddressRouting, id);
    }

}