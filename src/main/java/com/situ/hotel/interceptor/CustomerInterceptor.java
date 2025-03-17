package com.situ.hotel.interceptor;

import com.situ.hotel.domain.entity.Customer;
import com.situ.hotel.domain.vo.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.regex.Pattern;

@Component
public class CustomerInterceptor implements HandlerInterceptor {

    // 使用正则表达式匹配 /customer/customer/{id} 的路径
    private static final Pattern ID_PATH_PATTERN = Pattern.compile("^/customer/customer/\\d+$");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求的URI
        String requestURI = request.getRequestURI();

        // 如果请求路径匹配 /customer/customer/{id} 的模式，则放行
        if (ID_PATH_PATTERN.matcher(requestURI).matches()) {
            return true; // 放行
        }

        // 从session中获取当前登录的用户
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("user");

        if (ObjectUtils.isEmpty(customer)) {
            // 没登录，返回错误信息 Result
            response.setStatus(403);  // 身份验证未通过
            response.setContentType("application/json;charset=utf-8"); // 更正 contentType
            response.getWriter().append(Result.error("当前用户未登录!").toJson());
            // 拦截
            return false;
        } else {
            // 登录了，放行
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        // 在处理器执行后，执行
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        // 在请求完成后，执行
    }
}