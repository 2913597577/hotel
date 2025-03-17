package com.situ.hotel.service;

import com.github.pagehelper.PageInfo;
import com.situ.hotel.domain.entity.Reply;

public interface ReplyService {

    int add(Reply reply) throws Exception;
    PageInfo search(Integer page, Integer size, Reply reply);

}
