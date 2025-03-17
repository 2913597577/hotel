package com.situ.hotel.controller.user;

import com.github.pagehelper.PageInfo;
import com.situ.hotel.domain.entity.CheckOut;
import com.situ.hotel.domain.vo.Result;
import com.situ.hotel.service.CheckOutService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/checkout")
@RequiredArgsConstructor
public class CheckOutController {
    private final CheckOutService checkOutService;

    @PostMapping
    public Result add(@RequestBody CheckOut checkOut) {
        try {
            checkOutService.add(checkOut);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Result getById(Integer id) {
        return Result.success(checkOutService.getById(id));
    }

    @GetMapping()
    public Result search(Integer page,Integer size,CheckOut checkOut) {
        PageInfo pageInfo = checkOutService.search(page, size, checkOut);
        return Result.success(pageInfo);
    }
}
