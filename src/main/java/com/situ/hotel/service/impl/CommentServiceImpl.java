package com.situ.hotel.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.hotel.domain.entity.Comment;
import com.situ.hotel.mapper.CommentMapper;
import com.situ.hotel.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentMapper commentMapper;
    @Override
    public int add(Comment comment) throws Exception {
        return commentMapper.insert(comment);
    }

    @Override
    public PageInfo search(Integer page, Integer size, Comment comment) {
        PageHelper.startPage(page, size);
        //2- 查询
        List list=commentMapper.select(comment); //执行时自动拼接limit
        // 3 -创建分页对象
        return new PageInfo(list);
    }
}
