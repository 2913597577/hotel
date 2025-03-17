package com.situ.hotel.service;

import com.github.pagehelper.PageInfo;
import com.situ.hotel.domain.entity.Customer;
import com.situ.hotel.domain.entity.User;

import java.util.List;

public interface CustomerService {

    int add(Customer customer) throws Exception;

    int remove(Integer id) throws Exception;

    int edit(Customer customer) throws Exception;

    int editPassword(Customer customer) throws Exception;

    Customer login(Customer customer) throws Exception;

    Customer getById(Integer id);

    PageInfo search(Integer page, Integer size, Customer customer); ;
}
