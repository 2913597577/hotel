package com.situ.hotel.interceptor;

import com.situ.hotel.domain.entity.User;
import com.situ.hotel.domain.vo.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class UserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        //在处理器执行前，执行
        //从session中获取当前登录的用户
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (ObjectUtils.isEmpty(user)) {
            //没登录，
            // 返回错误信息  Result
            response.setStatus(403);  // 身份验证未通过
            response.setContentType("/application/json;charset=utf-8");
            response.getWriter().append(Result.error("当前用户未登录!").toJson());
            // 拦截
            return false;
        }else {
            //登录了，放行
            return true;
        }
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        //在处理器执行后，执行
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception{


    }
}
