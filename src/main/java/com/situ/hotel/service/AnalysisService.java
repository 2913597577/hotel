package com.situ.hotel.service;

import com.situ.hotel.domain.vo.AnalysisVo;

import java.util.List;

public interface AnalysisService {
    List<AnalysisVo> select();
    List<AnalysisVo> selectByStatus();
    List<AnalysisVo> selectByMonth(Integer year,Integer month);
}
