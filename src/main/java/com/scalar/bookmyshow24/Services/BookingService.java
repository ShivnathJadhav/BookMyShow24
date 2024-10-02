package com.scalar.bookmyshow24.Services;

import com.scalar.bookmyshow24.Exception.ShowNotFoundException;
import com.scalar.bookmyshow24.Exception.UserNotFoundException;
import com.scalar.bookmyshow24.Models.*;
import com.scalar.bookmyshow24.Repository.BookingRepository;
import com.scalar.bookmyshow24.Repository.ShowRepository;
import com.scalar.bookmyshow24.Repository.ShowSeatRepository;
import com.scalar.bookmyshow24.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class BookingService {

    private UserRepository userRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private BookingRepository bookingRepository;
    private PriceCalculatorService priceCalculatorService;

    @Autowired
    public BookingService(UserRepository userRepository,
                          ShowRepository showRepository,
                          ShowSeatRepository showSeatRepository,
                          BookingRepository bookingRepository) {
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.bookingRepository = bookingRepository;
    }
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookMovie(Long userID, Long showID, List<Long> showSeatID){
        /*
            Steps :- (Reference : Approach#1 from notes.)
            -------------TAKE A LOCK----------------
            1. Get the user from userId.
            2. Get the show from showId.
            3. Get the list of showSeats from showSeatIds.
            4. Check if all the show seats are available.
            5. If all the show seats are not available then throw an exception.
            6. If all are available, then change the status to be LOCKED.
            7. Change the status in DB as well.
            8. Create the Booking Object, and store it in DB.
            9. Return the Booking Object.
            -----------RELEASE THE LOCK---------------
         */

        //1. Get the user from userId.
        Optional<User> optionalUser = userRepository.findById(userID);

        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("User not found");
        }

        User bookedBy = optionalUser.get();

        //2. Get the show from showId.
        Optional<Show> optionalShow = showRepository.findById(showID);

        if(optionalShow.isEmpty()){
            throw new ShowNotFoundException("Show not found");
        }

        Show show = optionalShow.get();

        //3. Get the list of showSeats from showSeatIds.
        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatID);

        //4. Check if all the show seats are available.

        for(ShowSeat showSeat : showSeats){
            if(!showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)){
                //5. If all the show seats are not available then throw an exception.
                throw new ShowNotFoundException("Show Seat with ID "+showSeat.getId()+" Is Not Available");
            }
        }

        List<ShowSeat> bookedShowSeats = new ArrayList<>();
        //6. If all are available, then change the status to be LOCKED.
        for(ShowSeat showSeat : showSeats){
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
            //7. Change the status in DB as well.
            //showSeatRepository.save(showSeat);
            bookedShowSeats.add(showSeatRepository.save(showSeat));
        }

        //8. Create the Booking Object, and store it in DB.

        Booking booking = new Booking();
        booking.setShowSeats(bookedShowSeats);
        booking.setUser(bookedBy);
        booking.setBookingStatus(BookingStatus.IN_PROGRESS);
        booking.setPayments(new ArrayList<>());
        booking.setCreatedAt(new Date());
        booking.setLastModifiedAt(new Date());
        booking.setAmount(priceCalculatorService.calculateBookingPricw(bookedShowSeats,show));

        return bookingRepository.save(booking);

        // ---Lock will be release---
    }

}
