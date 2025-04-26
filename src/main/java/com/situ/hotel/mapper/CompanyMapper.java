package com.situ.hotel.mapper;

import com.github.pagehelper.PageInfo;
import com.situ.hotel.domain.entity.Company;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompanyMapper {

    int insert(Company company);

    int delete(Integer companyid);

    int update(Company company);

    Company selectById(Integer companyid);

    List<Company> select(Company company);
    List<Company> selectByUserId(Integer userid);

}
