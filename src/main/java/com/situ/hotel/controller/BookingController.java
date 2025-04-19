package com.situ.hotel.controller;

import com.github.pagehelper.PageInfo;
import com.situ.hotel.domain.entity.Booking;
import com.situ.hotel.domain.vo.Result;
import com.situ.hotel.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public Result add(@RequestBody Booking booking) {
        try {
            bookingService.add(booking);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @DeleteMapping
    public Result remove(Integer id) {
        try {
            bookingService.remove(id);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Result getById(Integer id) {
        return Result.success(bookingService.getById(id));
    }

    @GetMapping()
    public Result search(Integer page, Integer size, Booking booking) {

        PageInfo pageInfo = bookingService.search(page, size, booking);

        return Result.success(pageInfo);
    }

}
