package com.kusitms.hackathon.domain.user.domain.service;

import com.kusitms.hackathon.domain.user.domain.User;
import com.kusitms.hackathon.domain.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFactory {
    private final UserRepository userRepository;


    public User createUser(final String sub){
        return userRepository.findBySub(sub).orElseGet(() -> {
            User user = User.createNewUser(sub);
            return userRepository.save(user);
        });
    }
}
