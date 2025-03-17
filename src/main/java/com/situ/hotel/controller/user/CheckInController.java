package com.situ.hotel.controller.user;

import com.github.pagehelper.PageInfo;
import com.situ.hotel.domain.entity.CheckIn;
import com.situ.hotel.domain.vo.Result;
import com.situ.hotel.service.CheckInService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/checkin")
public class CheckInController {
    private final CheckInService checkInService;

    @PostMapping
    public Result add(@RequestBody CheckIn checkIn) {
        try {
            checkInService.add(checkIn);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Result getById(Integer id) {
        return Result.success(checkInService.getById(id));
    }

    @GetMapping()
    public Result search(Integer page,Integer size, CheckIn checkIn) {
        PageInfo pageInfo=checkInService.search(page,size,checkIn);
        return Result.success(pageInfo);
    }
}
