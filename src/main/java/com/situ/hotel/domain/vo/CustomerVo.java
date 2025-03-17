package com.situ.hotel.domain.vo;

import com.situ.hotel.domain.entity.Customer;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerVo extends Customer {
    private String captcha;
}
