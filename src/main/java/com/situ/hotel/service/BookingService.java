package com.situ.hotel.service;

import com.github.pagehelper.PageInfo;
import com.situ.hotel.domain.entity.Booking;

public interface BookingService {

    int add(Booking booking) throws Exception;

    int remove(Integer id) throws Exception;

    Booking getById(Integer id);

    PageInfo search(Integer page, Integer size, Booking booking);


}
