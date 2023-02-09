package com.bantads.orchestratorSaga.model;

import lombok.Data;

@Data
public class CustomerModel {
    private Long id;
    private String name;
    private String cpf;
    private Long address;
    private String phone;
    private Double salary;
}
