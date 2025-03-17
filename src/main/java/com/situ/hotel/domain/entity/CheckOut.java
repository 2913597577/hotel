package com.situ.hotel.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
public class CheckOut {
    private Integer checkoutid;
    private Integer bookingid;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GTM+8")
    private Date actualcheckouttime;
    private BigDecimal deposit;
    private String name;
    private String number;
}
