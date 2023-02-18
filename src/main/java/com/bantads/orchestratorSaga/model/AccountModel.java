package com.bantads.orchestratorSaga.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountModel {
    private String uuid;
    private String customer;
    private String manager;
    private Double limitAmount;
    private Double balance;
}
