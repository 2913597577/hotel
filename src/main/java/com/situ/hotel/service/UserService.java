package com.situ.hotel.service;

import com.github.pagehelper.PageInfo;
import com.situ.hotel.domain.entity.User;

import java.util.List;

public interface UserService {

    int reg (User user) throws Exception;

    User login (User user) throws Exception;

    int edit(User user) throws Exception;

    int editPassword (User user) throws Exception;

    int remove(Integer userid) throws Exception;

    //List<User> getAll(User user) ;

    User getById(Integer userid) ;

    PageInfo search(Integer page,Integer size,User user)  ;
}
