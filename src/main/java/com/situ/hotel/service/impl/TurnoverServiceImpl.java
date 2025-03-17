package com.situ.hotel.service.impl;

import com.situ.hotel.domain.vo.TurnoverVo;
import com.situ.hotel.mapper.TurnoverVoMapper;
import com.situ.hotel.service.TurnoverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TurnoverServiceImpl implements TurnoverService {
    private final TurnoverVoMapper turnoverVoMapper;
    @Override
    public List<TurnoverVo> select() {
        return turnoverVoMapper.select();
    }

    @Override
    public TurnoverVo selectByDate(Date date) {
        return turnoverVoMapper.selectByDate(date);
    }

    @Override
    public TurnoverVo selectByTime() {
        return turnoverVoMapper.selectByTime();
    }

    @Override
    public TurnoverVo selectByMonth(Integer year, Integer month) {
        return turnoverVoMapper.selectByMonth(year, month);
    }
}
