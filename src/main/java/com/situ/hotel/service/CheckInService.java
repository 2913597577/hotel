package com.situ.hotel.service;

import com.github.pagehelper.PageInfo;
import com.situ.hotel.domain.entity.CheckIn;

public interface CheckInService {
    int add(CheckIn checkIn) throws Exception;

    int delete(Integer id) throws Exception;

    CheckIn getById(Integer id);

    PageInfo search(Integer page, Integer size, CheckIn checkIn) ;
}
