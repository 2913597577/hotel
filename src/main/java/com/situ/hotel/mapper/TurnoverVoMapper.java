package com.situ.hotel.mapper;

import com.situ.hotel.domain.vo.TurnoverVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface TurnoverVoMapper {
    //查询全部
    List<TurnoverVo> select();
    TurnoverVo selectByDate(Date date);
    TurnoverVo selectByTime();
    TurnoverVo selectByMonth(Integer year, Integer month);
}
