package com.situ.hotel.domain.vo;

import com.situ.hotel.domain.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserVo extends User {
    private String captcha;
}
