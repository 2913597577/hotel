package com.situ.hotel.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.hotel.domain.entity.Company;
import com.situ.hotel.mapper.CompanyMapper;
import com.situ.hotel.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyMapper companyMapper;
    @Override
    public int add(Company company) throws Exception {
        return companyMapper.insert(company);
    }

    @Override
    public int remove(Integer companyid) throws Exception {
        return companyMapper.delete(companyid);
    }

    @Override
    public int edit(Company company) throws Exception {
        return companyMapper.update(company);
    }

    @Override
    public Company getById(Integer companyid) {
        return companyMapper.selectById(companyid);
    }

    @Override
    public PageInfo search(Integer page, Integer size, Company company) {
        PageHelper.startPage(page,size);
        List list=companyMapper.select(company);
        return new PageInfo(list);
    }

    @Override
    public List<Company> selectByUserId(Integer userid) {
        return companyMapper.selectByUserId(userid);
    }
}
