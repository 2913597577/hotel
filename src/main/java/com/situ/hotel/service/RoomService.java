package com.situ.hotel.service;

import com.github.pagehelper.PageInfo;
import com.situ.hotel.domain.entity.Room;
import com.situ.hotel.domain.entity.User;

import java.util.List;

public interface RoomService {

    int add(Room room) throws Exception;

    int remove(Integer roomid) throws Exception;

    int edit(Room room) throws Exception;

    Room getById(Integer roomid);

    //List<Room> getAll(Room room);
    Room getByNumber(String number) throws Exception;

    Room selectByNumber(String number) throws Exception;

    PageInfo search(Integer page, Integer size, Room room) throws Exception;
}
