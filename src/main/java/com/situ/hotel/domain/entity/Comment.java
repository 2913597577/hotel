package com.situ.hotel.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
public class Comment {
    private Integer commentid;
    private String content;
    private Integer bookingid;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GTM+8")
    private Date time;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GTM+8")
    private Date checkindate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GTM+8")
    private Date checkoutdate;
    private String name;
    private String typename;
    private String photo;
    private BigDecimal price;
    private Integer customerid;
}
