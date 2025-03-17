package com.situ.hotel.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Reply {
    private Integer replyid;
    private Integer userid;
    private String rcontent;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GTM+8")
    private Date replytime;
    private String username;
    private String ccontent;
    private Integer commentid;
    private Integer customerid;
}
