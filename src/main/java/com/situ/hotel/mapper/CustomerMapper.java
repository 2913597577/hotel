package com.situ.hotel.mapper;

import com.situ.hotel.domain.entity.Customer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CustomerMapper {
    //  添加/注册
    int insert(Customer customer);
    //登录
    Customer selectByPhone(String phone);
    //删除
    int delete(Integer customerid);
    //修改
    int update(Customer customer);
    //查找
    //根据id查询
    Customer selectById(Integer customerid);
    //模糊查询   姓名/电话
    List<Customer> select(Customer customer);
}
