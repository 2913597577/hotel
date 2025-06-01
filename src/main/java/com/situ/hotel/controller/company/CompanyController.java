package com.situ.hotel.controller.company;

import com.github.pagehelper.PageInfo;
import com.situ.hotel.domain.entity.Company;
import com.situ.hotel.domain.vo.Result;
import com.situ.hotel.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/company")
@RequiredArgsConstructor
@RestController
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public Result add(@RequestBody Company company){
        try {
            companyService.add(company);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }
    @PutMapping
    public Result edit(@RequestBody Company company){
        try {
            companyService.edit(company);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }
    @DeleteMapping
    public Result remove(@RequestParam Integer companyid){
        try {
            companyService.remove(companyid);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public Result getById(@PathVariable("id") Integer companyid){
        try {
            Company company = companyService.getById(companyid);
            return Result.success(company);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }
    @GetMapping
    public Result search(Integer page, Integer size, Company company) {
        if (page == null){
            List<Company> list = companyService.selectByUserId(company.getUserid());
            return Result.success(list);
        }
        try {
            PageInfo pageInfo = companyService.search(page, size, company);
            return Result.success(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }
}
