package com.bantads.orchestratorSaga.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManagerModel {
    private Long id;
    private String uuid;
    private String name;
    private String cpf;
    private String telephone;
}
