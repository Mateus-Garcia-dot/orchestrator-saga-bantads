package com.bantads.orchestratorSaga.model;

import lombok.Data;

@Data
public class AuthModel {
    private String id;
    private Long account;
    private String login;
    private String password;
    private Integer type;
    private Boolean isApproved;
    private Boolean isPending;
}
