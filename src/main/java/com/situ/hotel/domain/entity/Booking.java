package com.situ.hotel.domain.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
public class Booking implements Serializable {
    private Integer bookingid;
    private Integer customerid;
    private Integer roomid;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GTM+8")
    private Date checkindate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GTM+8")
    private Date checkoutdate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GTM+8")
    private Date bookingdate;
    private String name;
    private String phone;
    private BigDecimal price;
    private String typename;
    private String photo;
    private String area;
    private String facilities;
    private String roomName;
    private String companyname;
    private Integer companyid;
}
