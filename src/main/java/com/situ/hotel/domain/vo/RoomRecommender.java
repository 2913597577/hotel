package com.situ.hotel.domain.vo;

import com.situ.hotel.domain.entity.Booking;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoomRecommender extends Booking {
    private String area;
    private String facilities;
    private String name;

}
