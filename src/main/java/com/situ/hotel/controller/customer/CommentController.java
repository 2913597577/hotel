package com.situ.hotel.controller.customer;

import com.github.pagehelper.PageInfo;
import com.situ.hotel.domain.entity.Comment;
import com.situ.hotel.domain.vo.Result;
import com.situ.hotel.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;
    @GetMapping
    public Result search(Integer page, Integer size, Comment comment) {
        PageInfo pageInfo=commentService.search(page, size, comment);
        return Result.success(pageInfo);
    }
    @PostMapping
    public Result add(@RequestBody Comment comment) {
        try {
            commentService.add(comment);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @DeleteMapping
     public Result remove(@RequestParam Integer commentid) {
        try {
            commentService.remove(commentid);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }
}
