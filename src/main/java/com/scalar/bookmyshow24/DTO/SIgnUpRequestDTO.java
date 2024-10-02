package com.scalar.bookmyshow24.DTO;

import lombok.Getter;
import org.springframework.stereotype.Service;

@Getter
@Service
public class SIgnUpRequestDTO {
    private String email;

    private String password;
}
