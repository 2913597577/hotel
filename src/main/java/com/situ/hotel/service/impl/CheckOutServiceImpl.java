package com.situ.hotel.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.hotel.domain.entity.CheckOut;
import com.situ.hotel.mapper.CheckOutMapper;
import com.situ.hotel.service.CheckOutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CheckOutServiceImpl implements CheckOutService {
    private final CheckOutMapper checkOutMapper;
    @Override
    public int add(CheckOut checkOut) throws Exception {
        return checkOutMapper.insert(checkOut);
    }

    @Override
    public int delete(Integer id) throws Exception {
        return checkOutMapper.delete(id);
    }

    @Override
    public CheckOut getById(Integer id) {
        return checkOutMapper.selectById(id);
    }

    @Override
    public PageInfo search(Integer page, Integer size, CheckOut checkOut) {
        PageHelper.startPage(page, size);
        List list=checkOutMapper.select(checkOut);
        return new PageInfo(list);
    }
}
