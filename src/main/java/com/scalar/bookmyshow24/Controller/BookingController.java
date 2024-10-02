package com.scalar.bookmyshow24.Controller;

import com.scalar.bookmyshow24.DTO.BookMovieRequestDTO;
import com.scalar.bookmyshow24.DTO.BookMovieResponseDTO;
import com.scalar.bookmyshow24.Models.Booking;
import com.scalar.bookmyshow24.Models.ResponseStatus;
import com.scalar.bookmyshow24.Services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
@Controller
public class BookingController {
    private BookingService bookingService;
    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public BookMovieResponseDTO bookMovie(BookMovieRequestDTO bookMovieRequestDTO) {
        BookMovieResponseDTO bookMovieResponseDTO = new BookMovieResponseDTO();
        try{
            Booking booking = bookingService.bookMovie(bookMovieRequestDTO.getUserID(),
                    bookMovieRequestDTO.getShowID(),
                    bookMovieRequestDTO.getShowSeatId());
            bookMovieResponseDTO.setBookingID(booking.getId());
            bookMovieResponseDTO.setResponseStatus(ResponseStatus.SUCCESS);
            bookMovieResponseDTO.setAmount(booking.getAmount());

        } catch (RuntimeException e){
            bookMovieResponseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }
        return bookMovieResponseDTO;
    }
}
