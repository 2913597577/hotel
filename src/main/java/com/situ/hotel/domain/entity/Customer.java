package com.situ.hotel.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Customer {
    private Integer customerid;
    private String name;
    private String phone;
    private String idcard;
    private String password;
    private String repassword;
    private String oldpassword;
}
