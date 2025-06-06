package com.situ.hotel.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.hotel.domain.entity.Room;
import com.situ.hotel.domain.entity.User;
import com.situ.hotel.mapper.RoomMapper;
import com.situ.hotel.mapper.UserMapper;
import com.situ.hotel.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomMapper roomMapper;
    private final UserMapper userMapper;
    @Autowired
    private RoomRecommendationService roomRecommendationService;

    @Override
    public int add(Room room) throws Exception {
        return roomMapper.insert(room);
    }

    @Override
    public int remove(Integer roomid) throws Exception {
        return roomMapper.delete(roomid);
    }

    @Override
    public int edit(Room room) throws Exception {
        return roomMapper.update(room);
    }

    @Override
    public Room getById(Integer roomid) {
        return roomMapper.selectById(roomid);
    }

    @Override
    public Room getByNumber(String number) throws Exception {
        Room room = roomMapper.selectByNumber(number);
        if (ObjectUtils.isEmpty(room)) {
            throw new Exception("房间号错误!");
        }
        if (room.getStatus() != 0) {
            throw new Exception("该房间已满，请更换房间!");
        }
        roomMapper.updateStatus(number, 1);
        return room;
    }

    @Override
    public Room selectByNumber(String number) throws Exception {
        Room room = roomMapper.selectByNumber(number);
        if (ObjectUtils.isEmpty(room)) {
            throw new Exception("房间号错误!");
        }
        if (room.getStatus() == 0) {
            throw new Exception("该房间未入住，请核实房间号!");
        }
        roomMapper.updateStatus(number, 0);
        return room;
    }

    @Override
    public int delist(Integer number) {
        return roomMapper.updateIsDelist(number, 1);
    }


    @Override
    public PageInfo search(Integer page, Integer size, Room room) throws Exception {

        User user = userMapper.selectById(room.getUserid());
        if (user.getRole() == 1) {
            room.setUserid(null);
        }
        if (room.getLow() != null && room.getHigh() != null) {
            if (room.getLow() >= room.getHigh()) {
                throw new Exception("最低价格不能高于最高价格！");
            }
        }
        PageHelper.startPage(page, size);
        //2- 查询
        List list = roomMapper.select(room); //执行时自动拼接limit
        // 3 -创建分页对象
        return new PageInfo(list);
    }

    @Override
    public PageInfo search2(Integer page, Integer size, Room room) throws Exception {
        PageHelper.startPage(page, size);
        //2- 查询
        List list = roomMapper.select(room); //执行时自动拼接limit
        // 3 -创建分页对象
        return new PageInfo(list);
    }

    @Override
    public PageInfo search1(Integer page, Integer size, Room room, Integer customerid) throws Exception {
        if (room.getLow() != null && room.getHigh() != null) {
            if (room.getLow() >= room.getHigh()) {
                throw new Exception("最低价格不能高于最高价格！");
            }
        }

        // 调用推荐房间的方法
        List<Room> allRecommendedRooms = roomRecommendationService.recommendRooms(customerid, room);

        // 手动分页
        int total = allRecommendedRooms.size();
        int start = (page - 1) * size;
        int end = Math.min(start + size, total);

        List<Room> pagedRecommendedRooms = allRecommendedRooms.subList(start, end);

        // 创建分页对象
        PageInfo<Room> pageInfo = new PageInfo<>(pagedRecommendedRooms);
        pageInfo.setPageNum(page);
        pageInfo.setPageSize(size);
        pageInfo.setTotal(total);

        return pageInfo;
    }

}
