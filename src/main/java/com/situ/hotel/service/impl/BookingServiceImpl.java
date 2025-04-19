package com.situ.hotel.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.hotel.domain.entity.Booking;
import com.situ.hotel.mapper.BookingMapper;
import com.situ.hotel.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    //private final RedisUtil redisUtil;
    private final BookingMapper bookingMapper;

    @Autowired
    private final RoomRecommendationService roomRecommendationService;

    @Override
    public int add(Booking booking) throws Exception {
        //刷新Redis缓存
        //redisUtil.deletePattern("BookingCache:*");
        Booking booking1 = new Booking();
        booking1.setRoomid(booking.getRoomid());
        List<Booking> list = bookingMapper.select(booking1);
        for (Booking alreadyBooking : list) {
            Boolean flag1 = booking.getCheckindate().before(alreadyBooking.getCheckoutdate()) && booking.getCheckindate().after(alreadyBooking.getCheckindate());
            Boolean flag2 = booking.getCheckoutdate().before(alreadyBooking.getCheckoutdate()) && booking.getCheckoutdate().after(alreadyBooking.getCheckindate());
            if (flag1 || flag2) {
                throw new Exception("该房间已被预订");
            }
        }
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
        //String key="BookingCache:"+page+","+size+booking;
        //检查缓存中是否已经存在
        //PageInfo pageInfo=(PageInfo) redisUtil.get(key);
        //缓存中有，则直接返回缓存的数据
        //if(ObjectUtils.isEmpty(pageInfo)){
        //缓存没有，访问MySQL数据库进行查询
        PageHelper.startPage(page, size);
        List list = bookingMapper.select(booking);
        PageInfo pageInfo = new PageInfo(list);
        //先缓存起来，保存到Redis中
        //redisUtil.set(key,pageInfo);
        //}
        //返回
        return pageInfo;
    }
}
