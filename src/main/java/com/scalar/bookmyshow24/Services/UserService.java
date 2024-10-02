package com.scalar.bookmyshow24.Services;

import com.scalar.bookmyshow24.Exception.UserNotFoundException;
import com.scalar.bookmyshow24.Models.User;
import com.scalar.bookmyshow24.Repository.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Getter
@Setter
@Service
public class UserService {
    private UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User longIn(String email, String password){
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isEmpty()){
            throw new RuntimeException("Invalid email");
        }
        return null;
    }

    public User signUp(String email, String password){
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isPresent()){
            longIn(email, password);
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword();
    }
}
