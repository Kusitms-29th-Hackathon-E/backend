package com.kusitms.hackathon.domain.user.domain;

import java.util.Optional;

public interface UserRepository {
    User save(User user);

    Optional<User> findBySub(String sub);
}
