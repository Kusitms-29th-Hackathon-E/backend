package com.kusitms.hackathon.domain.user.domain.service;

import com.kusitms.hackathon.domain.user.domain.User;
import com.kusitms.hackathon.domain.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAppender {
    private final UserRepository userRepository;


    public void appendUser(String sub){
        User user = User.createNewUser( sub);
        userRepository.save(user);
    }
}
