package com.situ.hotel.service;

import com.github.pagehelper.PageInfo;
import com.situ.hotel.domain.entity.Comment;

public interface CommentService {
    int add(Comment comment) throws Exception;
    PageInfo search(Integer page, Integer size, Comment comment);

     int remove(Integer commentid) throws Exception;

     int edit(Comment comment) throws Exception;
}
