package com.bantads.orchestratorSaga.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComposedCustomerModel {
    private String uuid;
    private String name;
    private String cpf;
    private String phone;
    private Double salary;
    private AddressModel address;
    private AuthModel authentication;

    public CustomerModel getCustomer(){
        return new CustomerModel(uuid, name, cpf, address.getUuid(), phone, salary);
    }
}
