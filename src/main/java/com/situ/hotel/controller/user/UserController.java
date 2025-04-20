package com.situ.hotel.controller.user;

import com.github.pagehelper.PageInfo;
import com.situ.hotel.domain.entity.User;
import com.situ.hotel.domain.vo.Result;
import com.situ.hotel.domain.vo.UserVo;
import com.situ.hotel.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/user")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173/")
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody UserVo user, HttpSession session) {
        //验证码的校验
        String captcha = (String) session.getAttribute("captcha");
        if (ObjectUtils.isEmpty(captcha) || !captcha.equalsIgnoreCase(user.getCaptcha())) {
            //验证码错误
            return Result.error("验证码错误！");
        }
        //调用service
        try {
            User u = userService.login(user);
            //登录成功，保存登录信息，返回数据
            session.setAttribute("user", u);
            return Result.success(u);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/getLogin")
    public Result getLogin(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (!ObjectUtils.isEmpty(user)) {
            //登陆过了
            return Result.success(user);
        } else {
            return Result.error("员工未登录！");
        }
    }

    @GetMapping("/logout")
    public Result logout(HttpSession session) {
        session.invalidate();
        return Result.success();
    }

    @PostMapping("/reg")
    public Result reg(@RequestBody User user) {
        try {
            userService.reg(user);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @PutMapping
    public Result edit(@RequestBody User user) {
        try {
            userService.edit(user);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @PutMapping("/editPwd")
    public Result editPwd(@RequestBody User user) {
        try {
            userService.editPassword(user);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        return Result.success(userService.getById(id));
    }

    // 分页查询
    @GetMapping()
    public Result search(Integer page, Integer size, User user) {
        //1-获取参数
        //2-获取业务
        PageInfo pageInfo = userService.search(page, size, user);
        //3-返回数据
        return Result.success(pageInfo);
    }
}
