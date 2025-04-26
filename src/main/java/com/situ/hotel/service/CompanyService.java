package com.situ.hotel.service;

import com.github.pagehelper.PageInfo;
import com.situ.hotel.domain.entity.Company;

import java.util.List;

public interface CompanyService {
    int add(Company company) throws Exception;

    int remove(Integer companyid) throws Exception;

    int edit(Company company) throws Exception;

    Company getById(Integer companyid);

    PageInfo search(Integer page, Integer size, Company company);

    List<Company> selectByUserId(Integer userid);
}
