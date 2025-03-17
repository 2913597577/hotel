package com.situ.hotel.service.impl;

import com.situ.hotel.domain.vo.AnalysisVo;
import com.situ.hotel.mapper.AnalysisMapper;
import com.situ.hotel.service.AnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalysisServiceImpl implements AnalysisService {
    private  final AnalysisMapper analysisMapper;
    @Override
    public List<AnalysisVo> select() {
        return analysisMapper.select();
    }

    @Override
    public List<AnalysisVo> selectByStatus() {
        return analysisMapper.selectByStatus();
    }

    @Override
    public List<AnalysisVo> selectByMonth(Integer year, Integer month) {
        return analysisMapper.selectByMonth(year, month);
    }
}
