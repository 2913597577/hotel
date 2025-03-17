package com.situ.hotel.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.hotel.domain.entity.Booking;
import com.situ.hotel.mapper.BookingMapper;
import com.situ.hotel.service.BookingService;
import com.situ.hotel.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final RedisUtil redisUtil;
    private final BookingMapper bookingMapper;
    @Override
    public int add(Booking booking) throws Exception {
        //刷新Redis缓存
        redisUtil.deletePattern("BookingCache:*");
        return bookingMapper.insert(booking);
    }

    @Override
    public int remove(Integer id) throws Exception {
        return bookingMapper.delete(id);
    }

    @Override
    public Booking getById(Integer id) {
        return bookingMapper.selectById(id);
    }


    @Override
    public PageInfo search(Integer page, Integer size, Booking booking) {
        //生成一个key
        String key="BookingCache:"+page+","+size+booking;
        //检查缓存中是否已经存在
        PageInfo pageInfo=(PageInfo) redisUtil.get(key);
        //缓存中有，则直接返回缓存的数据
        if(ObjectUtils.isEmpty(pageInfo)){
            //缓存没有，访问MySQL数据库进行查询
            PageHelper.startPage(page, size);
            List list=bookingMapper.select(booking);
            pageInfo= new PageInfo(list);
            //先缓存起来，保存到Redis中
            redisUtil.set(key,pageInfo);
        }
        //返回
       return pageInfo;
    }
}
