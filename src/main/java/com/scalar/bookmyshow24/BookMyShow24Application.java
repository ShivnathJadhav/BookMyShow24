package com.scalar.bookmyshow24;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookMyShow24Application {

    public static void main(String[] args) {
        SpringApplication.run(BookMyShow24Application.class, args);
    }

}
