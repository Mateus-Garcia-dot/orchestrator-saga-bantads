package com.bantads.orchestratorSaga.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterModel {
    private AuthModel authentication;
    private CustomerModel customer;
    private AddressModel address;
    private AccountModel account;
}
