package com.situ.hotel.service;

import com.situ.hotel.domain.vo.TurnoverVo;

import java.util.Date;
import java.util.List;

public interface TurnoverService {
    List<TurnoverVo> select();
    TurnoverVo selectByDate(Date date);
    TurnoverVo selectByTime();
    TurnoverVo selectByMonth(Integer year, Integer month);
}
