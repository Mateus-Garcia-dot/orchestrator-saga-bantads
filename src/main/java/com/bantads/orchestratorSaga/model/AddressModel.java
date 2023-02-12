package com.bantads.orchestratorSaga.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressModel {
    private String uuid;
    private String type;
    private String street;
    private Integer number;
    private String city;
    private String complement;
    private String cep;
    private String state;
}
