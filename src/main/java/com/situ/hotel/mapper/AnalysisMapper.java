package com.situ.hotel.mapper;

import com.situ.hotel.domain.vo.AnalysisVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnalysisMapper {
    List<AnalysisVo> select();
    List<AnalysisVo> selectByStatus();
    List<AnalysisVo> selectByMonth(Integer year,Integer month);
}
