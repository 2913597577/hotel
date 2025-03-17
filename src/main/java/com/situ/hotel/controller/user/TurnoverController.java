package com.situ.hotel.controller.user;

import com.situ.hotel.domain.vo.Result;
import com.situ.hotel.service.TurnoverService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/turnover")
public class TurnoverController {
    private final TurnoverService turnoverService;
    @GetMapping
    public Result get(){
        return Result.success(turnoverService.select());
    }
    @GetMapping("/date")
    public Result getByDate(Date date){
        return Result.success(turnoverService.selectByDate(date));
    }
    @GetMapping("/time")
    public Result getByTime(){
        return Result.success(turnoverService.selectByTime());
    }
    @GetMapping("/month")
    public Result getByMonth(Integer year,Integer month){
        return Result.success(turnoverService.selectByMonth(year,month));
    }
}
