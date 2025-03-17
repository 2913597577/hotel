package com.situ.hotel.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.hotel.domain.entity.CheckIn;
import com.situ.hotel.mapper.CheckInMapper;
import com.situ.hotel.service.CheckInService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CheckInServiceImpl implements CheckInService {
    private final CheckInMapper checkInMapper;

    @Override
    public int add(CheckIn checkIn) throws Exception {
        return checkInMapper.insert(checkIn);
    }

    @Override
    public int delete(Integer id) throws Exception {
        return checkInMapper.delete(id);
    }

    @Override
    public CheckIn getById(Integer id) {
        return checkInMapper.selectById(id);
    }

    @Override
    public PageInfo search(Integer page, Integer size, CheckIn checkIn) {
        PageHelper.startPage(page, size);
        List list=checkInMapper.select(checkIn);
        return new PageInfo(list);
    }
}
