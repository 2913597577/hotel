package com.situ.hotel.controller.user;

import com.situ.hotel.domain.vo.Result;
import com.situ.hotel.service.AnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/analysis")
@RestController
public class AnalysisController {
    private final AnalysisService analysisService;

    @GetMapping()
    public Result get(){
        return Result.success(analysisService.select());
    }
    @GetMapping("/status")
    public Result getStatus(){
        return Result.success(analysisService.selectByStatus());
    }
}
