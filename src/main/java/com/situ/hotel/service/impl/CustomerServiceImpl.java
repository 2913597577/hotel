package com.situ.hotel.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.hotel.domain.entity.Customer;
import com.situ.hotel.mapper.CustomerMapper;
import com.situ.hotel.service.CustomerService;
import com.situ.hotel.util.MD5Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerMapper customerMapper;
    private static final String SALT=";lksdfujapboj";

    @Override
    public int add(Customer customer) throws Exception {
        //数据有效性的验证，交给Service层
        //数据格式的验证，交给Controller
        // 验证两次密码是否一样
        if(!customer.getPassword().equals(customer.getRepassword())){
            throw new Exception("两次密码不一样");
        }
        //验证手机号
        Customer sUser = customerMapper.selectByPhone(customer.getPhone());
        if(!ObjectUtils.isEmpty(sUser)){
            throw new Exception("手机号已被注册！");
        }
        //密码需要MD5加密   双重加盐的MD5加密
        String md5Pwd= MD5Util.getDBMD5(customer.getPassword(),SALT);
        System.out.println(md5Pwd);
        customer.setPassword(md5Pwd);
        //插入数据库
        return customerMapper.insert(customer);
    }

    @Override
    public int remove(Integer id) throws Exception {
        return customerMapper.delete(id);
    }

    @Override
    public int edit(Customer customer) throws Exception {
        return customerMapper.update(customer);
    }

    @Override
    public int editPassword(Customer customer) throws Exception {
        Customer cus=customerMapper.selectById(customer.getCustomerid());
        String Pwd=MD5Util.getDBMD5(customer.getOldpassword(),SALT);
        if(customer.getPassword()==null || customer.getPassword().isEmpty()
                ||customer.getRepassword()==null|| customer.getRepassword().isEmpty()
                || customer.getOldpassword()==null || customer.getOldpassword().isEmpty()
        ){
            throw new Exception("不可为空!");
        }
        if(!cus.getPassword().equals(Pwd)){
            throw new Exception("旧密码错误!");
        }
        if(!customer.getPassword().equals(customer.getRepassword())){
            throw new Exception("两次密码不一致!");
        }
        customer.setPassword(MD5Util.getDBMD5(customer.getPassword(),SALT));
        return customerMapper.update(customer);
    }

    @Override
    public Customer login(Customer customer) throws Exception {
        Customer cUser = customerMapper.selectByPhone(customer.getPhone());
        if(ObjectUtils.isEmpty(cUser)){
            throw new Exception("用户不存在!");
        }
        String md5Pwd= MD5Util.getDBMD5(customer.getPassword(),SALT);
        if(!cUser.getPassword().equals(md5Pwd)){
            throw new Exception("密码错误!");
        }
        return cUser;
    }

    @Override
    public Customer getById(Integer id) {
        return customerMapper.selectById(id);
    }

    @Override
    public PageInfo search(Integer page, Integer size, Customer customer) {
        PageHelper.startPage(page, size);
        //2- 查询
        List list=customerMapper.select(customer); //执行时自动拼接limit
        // 3 -创建分页对象
        return new PageInfo(list);
    }
}
