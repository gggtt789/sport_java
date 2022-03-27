package com.sport.sport.views;

import com.sport.sport.entities.Seat;

import java.math.BigDecimal;

public class SeatView {
    public Long id;
    public String name;
    public BigDecimal price;
    public Long total_number;
    public Long available_number;

    public SeatView() {}
    public SeatView(Seat seat) {
        id = seat.getId();
        name = seat.getName();
        price = seat.getPrice();
        total_number = seat.getTotalNumber();
        available_number = seat.getAvailableNumber();
    }
}
