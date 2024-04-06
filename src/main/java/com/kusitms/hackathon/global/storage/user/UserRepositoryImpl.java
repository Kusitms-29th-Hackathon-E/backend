package com.kusitms.hackathon.global.storage.user;

import com.kusitms.hackathon.domain.user.domain.User;
import com.kusitms.hackathon.domain.user.domain.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public Optional<User> findBySub(String sub) {
        return Optional.empty();
    }
}
