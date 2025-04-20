package com.situ.hotel.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Company {
    private Integer companyid;
    private String companyname;
    private String address;
    private Integer userid;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GTM+8")
    private Date registertime;
    private String phone;
}
