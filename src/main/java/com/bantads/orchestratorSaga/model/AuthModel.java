package com.bantads.orchestratorSaga.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthModel {
    private String uuid;
    private String account;
    private String login;
    private String password;
    private Integer type;
    private Boolean isApproved;
    private Boolean isPending;
}
