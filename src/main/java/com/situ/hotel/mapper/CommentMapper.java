package com.situ.hotel.mapper;

import com.situ.hotel.domain.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    int delete(Integer commentid);
    int insert(Comment comment);
    int update(Comment comment);
    List<Comment> select(Comment comment);
}
