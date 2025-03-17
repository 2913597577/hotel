package com.situ.hotel.controller.user;

import com.github.pagehelper.PageInfo;
import com.situ.hotel.domain.entity.Reply;
import com.situ.hotel.domain.vo.Result;
import com.situ.hotel.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reply")
public class ReplyController {
    private final ReplyService replyService;

    @PostMapping
    public Result add(@RequestBody Reply reply) {
        try {
            replyService.add(reply);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @GetMapping
    public Result search(Integer page, Integer size,Reply reply) {

        PageInfo pageInfo=replyService.search(page, size, reply);
        return Result.success(pageInfo);
    }
}
