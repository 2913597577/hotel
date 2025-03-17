package com.situ.hotel.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class User {
    //定义两个常量
    public static final Integer STATUS_DISABLE=1;
    public static final Integer STATUS_ENABLE=0;
    private Integer userid;
    private String username;
    private String password;
    private String sex;
    private Integer age;
    private Integer role;
    private String email;
    private String phone;
    private String idcard;
    private String address;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GTM+8")
    private Date startdate;
    private Integer status;
    private String repassword;
    private String oldpassword;
}
