package com.situ.hotel.controller.customer;


import com.github.pagehelper.PageInfo;
import com.situ.hotel.domain.entity.Customer;
import com.situ.hotel.domain.vo.CustomerVo;
import com.situ.hotel.domain.vo.Result;
import com.situ.hotel.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/customer/customer")
@RestController
@CrossOrigin("http://localhost:5173/")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/login")
    public Result login(@RequestBody CustomerVo customer, HttpSession session) {
        String captcha=(String) session.getAttribute("captcha");
        if(ObjectUtils.isEmpty(captcha)||!captcha.equalsIgnoreCase(customer.getCaptcha())){
            //验证码错误
            return Result.error("验证码错误！");
        }
        try {
            Customer c=customerService.login(customer);
            //登录成功，保存登录数据，返回数据
            session.setAttribute("customer",c);
            return Result.success(c);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }

    }

    @GetMapping("/getLogin")
    public Result getLogin(HttpSession session) {
        Customer customer=(Customer)session.getAttribute("customer");
        System.out.println(customer);
        if(!ObjectUtils.isEmpty(customer)){
            //登陆过了
            return Result.success(customer);
        }else {
            return Result.error("用户未登录！");
        }
    }

    @GetMapping("/logout")
    public Result logout(HttpSession session) {
        session.invalidate();
        return Result.success();
    }

    @PostMapping
    public Result add(@RequestBody Customer customer) {
        System.out.println(customer);
        try {
            customerService.add(customer);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }

    }

    @DeleteMapping
    public Result remove(Integer id) {
        try {
            customerService.remove(id);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @PutMapping
    public Result edit(@RequestBody Customer customer) {
        try {
            customerService.edit(customer);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }
    @PutMapping("/pwd")
    public Result editPwd(@RequestBody Customer customer) {
        try {
            customerService.editPassword(customer);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        return Result.success(customerService.getById(id));
    }
    @GetMapping
    public Result search(Integer page, Integer size, Customer customer) {
        PageInfo pageInfo=customerService.search(page, size, customer);
        return Result.success(pageInfo);
    }
}
