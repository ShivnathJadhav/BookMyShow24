package com.scalar.bookmyshow24.Services;

import com.scalar.bookmyshow24.Models.Show;
import com.scalar.bookmyshow24.Models.ShowSeat;
import com.scalar.bookmyshow24.Models.ShowSeatType;
import com.scalar.bookmyshow24.Repository.ShowSeatTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PriceCalculatorService {
    private ShowSeatTypeRepository showSeatTypeRepository;
    @Autowired
    public PriceCalculatorService(ShowSeatTypeRepository showSeatTypeRepository) {
        this.showSeatTypeRepository = showSeatTypeRepository;
    }

    public int calculateBookingPricw(List<ShowSeat> showSeatList, Show show) {
        List<ShowSeatType> shoeSeatTypes = showSeatTypeRepository.findALLByShow(show.getId());
        int amount=0;

        for(ShowSeat showSeat : showSeatList) {
            for(ShowSeatType showSeatType : shoeSeatTypes) {
                if(showSeatType.getSeatType().equals(showSeat.getSeat().getSeatType())){
                    amount+=showSeatType.getPrice();
                }
            }
        }
        return amount;
    }
}
