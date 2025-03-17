package com.situ.hotel.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.hotel.domain.entity.User;
import com.situ.hotel.mapper.UserMapper;
import com.situ.hotel.service.UserService;
import com.situ.hotel.util.MD5Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private static final String SALT=";lksdfujapboj";

    @Override
    public int reg(User user) throws Exception {
        //数据有效性的验证，交给Service层
        //数据格式的验证，交给Controller
        // 验证两次密码是否一样
        if(!user.getPassword().equals(user.getRepassword())){
            throw new Exception("两次密码不一样");
        }
        //验证手机号
        User sUser = userMapper.selectByPhone(user.getPhone());
        if(!ObjectUtils.isEmpty(sUser)){
            throw new Exception("手机号已被注册！");
        }
        //密码需要MD5加密   双重加盐的MD5加密
        String md5Pwd= MD5Util.getDBMD5(user.getPassword(),SALT);
        user.setPassword(md5Pwd);
        //插入数据库
        return userMapper.insert(user);
    }

    @Override
    public User login(User user) throws Exception {
        //验证账号是否正确
        User sUser=userMapper.selectByPhone(user.getPhone());
        if(ObjectUtils.isEmpty(sUser)){
            throw new Exception("用户不存在!");
        }
        //验证密码是否正确
        //MD5Util.getDBMD5(user.getPassword(),SALT);
        //user.getPassword();
        String md5Pwd=MD5Util.getDBMD5(user.getPassword(),SALT);
        if(!sUser.getPassword().equals(md5Pwd)){
            throw new Exception("密码错误!");
        }
        //验证用户状态
        if(sUser.getStatus().equals(User.STATUS_DISABLE)){
            throw new Exception("当前账户已被禁用，请联系Super管理员");
        }
        return sUser;
    }

    @Override
    public int edit(User user) throws Exception {
        return userMapper.update(user);
    }

    @Override
    public int editPassword(User user) throws Exception {
        User sUser=userMapper.selectById(user.getUserid());
        String md5Pwd= MD5Util.getDBMD5(user.getOldpassword(),SALT);
        //String md5Pwd=user.getPassword();
        if(user.getPassword()==null || user.getPassword().isEmpty()
           ||user.getRepassword()==null|| user.getRepassword().isEmpty()
            || user.getOldpassword()==null || user.getOldpassword().isEmpty()
        ){
            throw new Exception("不可为空!");
        }
        if(!sUser.getPassword().equals(md5Pwd)){
            throw new Exception("旧密码错误!");
        }
        if(!user.getPassword().equals(user.getRepassword())){
            throw new Exception("两次密码不一致!");
        }
        user.setPassword(MD5Util.getDBMD5(user.getPassword(),SALT));
        return userMapper.update(user);
    }

   /* @Override
    public int editStatus(Integer userid, Integer status) throws Exception {
        return userMapper.updateStatus(userid, status);
    }*/

    @Override
    public int remove(Integer userid) throws Exception {
        return userMapper.delete(userid);
    }


    @Override
    public User getById( Integer userid) {
        return userMapper.selectById(userid);
    }

    @Override
    public PageInfo search(Integer page, Integer size, User user) {

        // 1-开启分页
        PageHelper.startPage(page, size);
        //2- 查询
        List list=userMapper.select(user); //执行时自动拼接limit
        // 3 -创建分页对象
        return new PageInfo(list);
    }
}
