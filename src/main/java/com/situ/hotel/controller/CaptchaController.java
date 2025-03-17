package com.situ.hotel.controller;

import com.situ.hotel.util.CaptchaUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/captcha")
public class CaptchaController {

    //返回一张图片
    @GetMapping
    public void capthca(HttpServletResponse response, HttpSession session){
        //生成一张验证码的图片，放到response返回到客户端
        String captcha= CaptchaUtil.createCaptcha(response);
        session.setAttribute("captcha",captcha);
    }
}
