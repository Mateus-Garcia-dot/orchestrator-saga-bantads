package com.bantads.orchestratorSaga.model;

import lombok.Data;

@Data
public class AccountModel {
    private String uuid;
    private String customer;
    private String manager;
    private Double limitAmount;
    private Double balance;
}
