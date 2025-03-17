package com.situ.hotel.controller;

import com.situ.hotel.domain.vo.Result;
import com.situ.hotel.util.UploadUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传的接口
 */
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Value("${upload.path}")
    private String path;

    // 接收图片文件   只允许post请求
    @RequestMapping(method = RequestMethod.POST)
    public Result upload(MultipartFile file) {
        String fileName = UploadUtil.save(file,path);
        return Result.success(fileName);
    }
}
