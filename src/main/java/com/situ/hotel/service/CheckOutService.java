package com.situ.hotel.service;

import com.github.pagehelper.PageInfo;
import com.situ.hotel.domain.entity.CheckOut;

public interface CheckOutService {
    int add(CheckOut checkOut) throws Exception;
    int delete(Integer id) throws Exception;
    CheckOut getById(Integer id);
    PageInfo search(Integer page,Integer size,CheckOut checkOut);

}
