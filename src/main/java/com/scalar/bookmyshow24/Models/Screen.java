package com.scalar.bookmyshow24.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity

public class Screen extends BaseModel{
    private String screenNumber;

    @OneToMany
    private List<Seat> Seats;

    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    List<Feature> Features;

}
/*

   1   ->  M
Screen -- Seat => 1:M
   1    <-  1

 */