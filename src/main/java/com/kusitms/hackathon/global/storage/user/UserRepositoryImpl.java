package com.kusitms.hackathon.global.storage.user;

import com.kusitms.hackathon.domain.user.domain.User;
import com.kusitms.hackathon.domain.user.domain.UserRepository;
import com.kusitms.hackathon.global.storage.user.jpa.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;
    @Override
    public User save(User user) {
        return userMapper.toDomain(userJpaRepository.save(userMapper.toEntity(user)));
    }

    @Override
    public Optional<User> findBySub(String sub) {
        return userJpaRepository.findBySub(sub)
                .map(userMapper::toDomain);
    }
}
