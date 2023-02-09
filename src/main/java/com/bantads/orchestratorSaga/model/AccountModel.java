package com.bantads.orchestratorSaga.model;

import lombok.Data;

@Data
public class AccountModel {
    private Long id;
    private Long customer;
    private Long manager;
    private Double limitAmount;
    private Double balance;
}
