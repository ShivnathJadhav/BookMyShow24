package com.scalar.bookmyshow24.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@Entity (name = "Shows")

public class Show extends BaseModel{

    @ManyToOne
    private Movie movie;

    private Date startTime;
    private Date endTime;

    @ManyToOne
    private Screen screen;

    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<Feature> features;

}

/*
1. Cardinality Mapping for all the attributes.
2. Build the code.
3. First Requirement : User should be able to book the ticket.
   [If more than one user is trying to book the same seat for a show then only one
   user should be allowed to book -> Handle Concurrency]

 */

/*

  1 ->   1
Show   Movie => M:1
  M <-  1


  1 ->   1
Show   Screen
  M  <-   1


   1   ->  M
  Show   Features ->  M:M
    M   <-     1
 */