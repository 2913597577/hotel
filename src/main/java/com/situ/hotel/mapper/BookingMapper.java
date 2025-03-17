package com.situ.hotel.mapper;
import com.situ.hotel.domain.entity.Booking;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookingMapper {

    int insert(Booking booking);

    int delete(Integer bookingid);

    Booking selectById(Integer bookingid);

    //根据客户姓名查询 /
    List<Booking> select(Booking booking);

}
