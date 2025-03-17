package com.situ.hotel.mapper;

import com.situ.hotel.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    //  添加/注册
    int insert(User user);
    //登录
    User selectByPhone(String phone);
    //删除
    int delete(Integer userid);
    //修改
    int update(User user);
    //int updateStatus(Integer userid,Integer status);
    //查找
    //根据员工id查询
    User selectById(Integer userid);
    //模糊查询   姓名/性别/电话/身份证号
    List<User> select(User user);
}
