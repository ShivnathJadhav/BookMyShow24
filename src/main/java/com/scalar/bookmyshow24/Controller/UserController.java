package com.scalar.bookmyshow24.Controller;

import com.scalar.bookmyshow24.DTO.SIgnUpRequestDTO;
import com.scalar.bookmyshow24.DTO.SignUpResponceDTO;
import com.scalar.bookmyshow24.Models.User;
import com.scalar.bookmyshow24.Repository.UserRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Getter
@Service
@Controller
public class UserController {
    private UserRepository userRepository;
    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public SignUpResponceDTO signUP(SIgnUpRequestDTO sIgnUpRequestDTO){
        Optional<User> optionalUser = userRepository.findByEmail(sIgnUpRequestDTO.getEmail());

    }
}
