package com.situ.hotel.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.hotel.domain.entity.Reply;
import com.situ.hotel.mapper.ReplyMapper;
import com.situ.hotel.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReplyServiceImpl implements ReplyService {

    private final ReplyMapper replyMapper;
    @Override
    public int add(Reply reply) throws Exception {
        return replyMapper.insert(reply);
    }

    @Override
    public PageInfo search(Integer page, Integer size, Reply reply) {
        PageHelper.startPage(page,size);

        List list=replyMapper.select(reply);

        return new PageInfo(list);
    }
}
