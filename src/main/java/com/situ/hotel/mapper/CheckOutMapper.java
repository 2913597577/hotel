package com.situ.hotel.mapper;

import com.situ.hotel.domain.entity.CheckOut;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CheckOutMapper {
    int insert (CheckOut checkOut);

    int delete (Integer checkoutid);

    CheckOut selectById(Integer checkoutid);

    //根据
    List<CheckOut> select(CheckOut checkOut);
}
