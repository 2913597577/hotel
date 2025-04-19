package com.situ.hotel.service.impl;

import com.github.pagehelper.PageHelper;
import com.situ.hotel.domain.entity.Booking;
import com.situ.hotel.domain.entity.Room;
import com.situ.hotel.mapper.BookingMapper;
import com.situ.hotel.mapper.RoomMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class RoomRecommendationService {

    private final BookingMapper bookingMapper;
    private final RoomMapper roomMapper;

    // 计算两个房间之间的相似度
    private double calculateSimilarity(Booking booking, Room room) {
        // 价格相似度
        double priceSimilarity = 1 - booking.getPrice().subtract(room.getPrice()).abs().doubleValue()
                / Math.max(booking.getPrice().doubleValue(), room.getPrice().doubleValue());

        // 设备相似度
        Set<String> facilities1 = new HashSet<>(Arrays.asList(booking.getFacilities().split(",")));
        Set<String> facilities2 = new HashSet<>(Arrays.asList(room.getFacilities().split(",")));
        Set<String> intersection = new HashSet<>(facilities1);
        intersection.retainAll(facilities2);
        double facilitySimilarity = (double) intersection.size() / Math.max(facilities1.size(), facilities2.size());

        // 房间 类型相似度
        double typeSimilarity = booking.getTypename().equals(room.getTypename()) ? 1 : 0;

        // 房间 面积相似度
        double area1 = Double.parseDouble(booking.getArea());
        double area2 = Double.parseDouble(room.getArea());
        double areaSimilarity = 1 - Math.abs(area1 - area2) / Math.max(area1, area2);

        // 综合相似度，可根据实际情况调整权重
        return 0.3 * priceSimilarity + 0.3 * facilitySimilarity + 0.2 * typeSimilarity + 0.2 * areaSimilarity;
    }

    // 为用户推荐房间
    public List<Room> recommendRooms(Integer customerId) {
        // 禁用 PageHelper 对 BookingMapper.selectByCustomerId 的影响
        PageHelper.clearPage();
        List<Booking> userBookings = bookingMapper.selectByCustomerId(customerId);

        // 禁用 PageHelper 对 RoomMapper.select 的影响
        List<Room> rooms = roomMapper.select(null);

        // 计算每个房间与用户历史订单房间的相似度
        Map<Room, Double> roomScores = new HashMap<>();
        for (Room room : rooms) {
            double score = 0;
            for (Booking userBooking : userBookings) {
                score += calculateSimilarity(userBooking, room);
            }
            roomScores.put(room, score);
        }

        // 对房间按相似度得分排序
        List<Map.Entry<Room, Double>> sortedEntries = new ArrayList<>(roomScores.entrySet());
        sortedEntries.sort(Map.Entry.<Room, Double>comparingByValue().reversed());

        // 返回所有推荐的房间
        List<Room> recommendedRooms = new ArrayList<>();
        for (Map.Entry<Room, Double> entry : sortedEntries) {
            recommendedRooms.add(entry.getKey());
        }

        return recommendedRooms;
    }
}
