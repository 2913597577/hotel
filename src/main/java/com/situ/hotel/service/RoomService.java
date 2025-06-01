package com.situ.hotel.service;

import com.github.pagehelper.PageInfo;
import com.situ.hotel.domain.entity.Room;

public interface RoomService {

    int add(Room room) throws Exception;

    int remove(Integer roomid) throws Exception;

    int edit(Room room) throws Exception;

    Room getById(Integer roomid);

    //List<Room> getAll(Room room);
    Room getByNumber(String number) throws Exception;

    Room selectByNumber(String number) throws Exception;

    int delist(Integer number);

    PageInfo search(Integer page, Integer size, Room room) throws Exception;

    PageInfo search2(Integer page, Integer size, Room room) throws Exception;

    PageInfo search1(Integer page, Integer size, Room room, Integer customerid) throws Exception;
}
