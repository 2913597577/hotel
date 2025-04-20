package com.situ.hotel.controller.user;

import com.github.pagehelper.PageInfo;
import com.situ.hotel.domain.entity.Room;
import com.situ.hotel.domain.vo.Result;
import com.situ.hotel.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/room")
public class RoomController {
    private final RoomService roomService;

    @PostMapping
    public Result add(@RequestBody Room room) {
        try {
            roomService.add(room);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @PutMapping
    public Result edit(@RequestBody Room room) {
        try {
            roomService.edit(room);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @DeleteMapping
    public Result remove(Integer roomid) {
        try {
            roomService.remove(roomid);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Result getById(Integer roomid) {
        return Result.success(roomService.getById(roomid));
    }

    @GetMapping()
    public Result search(Integer page, Integer size, Room room) {
        //1-获取参数
        //2-获取业务
        try {
            PageInfo pageInfo;
            if (room.getCustomerid() == null) {
                pageInfo = roomService.search(page, size, room);
            } else {
                pageInfo = roomService.search1(page, size, room, room.getCustomerid());
            }

            //3-返回数据
            return Result.success(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/numberIn")
    public Result getByNumber(String number) {
        try {
            roomService.getByNumber(number);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/numberOut")
    public Result selectByNumber(String number) {
        try {
            roomService.selectByNumber(number);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/delist")
    public Result delist(Integer number) {
        try {
            roomService.delist(number);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

}
