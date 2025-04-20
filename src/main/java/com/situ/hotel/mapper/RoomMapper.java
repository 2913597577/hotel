package com.situ.hotel.mapper;

import com.situ.hotel.domain.entity.Room;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoomMapper {
    //  添加
    int insert(Room room);

    //删除
    int delete(Integer roomid);

    //修改
    int update(Room room);

    int updateStatus(String number, Integer status);

    int updateIsDelist(Integer roomid, Integer isdelsit);

    //查找
    //根据id查询
    Room selectById(Integer roomid);

    Room selectByNumber(String number);

    //模糊查询   /名字/设施/门牌号/房间类型
    List<Room> select(Room room);

    //根据价格查询
    List<Room> selectByPrice(String low, String high);

}
