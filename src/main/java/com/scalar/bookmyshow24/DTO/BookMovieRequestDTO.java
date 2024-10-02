package com.scalar.bookmyshow24.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class BookMovieRequestDTO {
    private List<Long> showSeatId;
    private Long userID;
    private Long showID;
}
