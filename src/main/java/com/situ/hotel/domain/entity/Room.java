package com.situ.hotel.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class Room {
    private Integer roomid;
    private Integer userid;
    private String number;
    private String name;
    private String typeid;
    private BigDecimal price;
    private Integer status;
    private String area;
    private String facilities;
    private String typename;
    private String photo;
    private Double low;
    private Double high;
    private Integer customerid;
    private String companyname;
    private String address;
    private String phone;
    private Integer isdelist;
}
