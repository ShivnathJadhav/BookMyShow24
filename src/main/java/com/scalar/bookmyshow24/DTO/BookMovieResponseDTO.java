package com.scalar.bookmyshow24.DTO;

import com.scalar.bookmyshow24.Models.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookMovieResponseDTO {
    private Long bookingID;
    private double Amount;
    private ResponseStatus responseStatus;
}
