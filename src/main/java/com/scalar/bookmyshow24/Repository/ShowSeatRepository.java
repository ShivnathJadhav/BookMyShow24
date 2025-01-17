package com.scalar.bookmyshow24.Repository;

import com.scalar.bookmyshow24.Models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {
    @Override
    Optional<ShowSeat> findById(Long aLong);

    @Override
    ShowSeat save(ShowSeat showSeat); //Upsert -> Update + Insert.
    //If showSeat object is not there in the DB, then insert it.
    //Else update the showSeat object in the DB.

}
