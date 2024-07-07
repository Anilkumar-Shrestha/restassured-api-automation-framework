package com.api.models.pojo.booking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(setterPrefix = "set")
public class BookingDates {

    private String checkin;
    private String checkout;
}
