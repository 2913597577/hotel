package com.situ.hotel.mapper;

import com.situ.hotel.domain.entity.Reply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReplyMapper {

    int insert(Reply reply);

    List<Reply> select(Reply reply);

}
