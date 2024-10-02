package com.scalar.bookmyshow24.DTO;

import com.scalar.bookmyshow24.Models.ResponseStatus;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Getter
@Service
public class SignUpResponceDTO {
    private Long userId;
    private ResponseStatus responseStatus;

}
