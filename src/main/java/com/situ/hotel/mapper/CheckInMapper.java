package com.situ.hotel.mapper;

import com.situ.hotel.domain.entity.CheckIn;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CheckInMapper {

    int insert (CheckIn checkIn);

    int delete (Integer checkinid);

    CheckIn selectById(Integer checkinid);

    //根据
    List<CheckIn> select(CheckIn checkIn);
}
