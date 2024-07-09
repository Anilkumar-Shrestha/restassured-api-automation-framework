package com.api.models.pojo.booking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder(setterPrefix = "set")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseBookingCreated {

    private Integer bookingid;
    private BookingBody booking;

}
