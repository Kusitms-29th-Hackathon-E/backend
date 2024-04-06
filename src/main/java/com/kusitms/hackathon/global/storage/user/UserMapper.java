package com.kusitms.hackathon.global.storage.user;

import com.kusitms.hackathon.domain.user.domain.User;
import com.kusitms.hackathon.global.storage.user.jpa.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity toEntity(User user) {
        return UserEntity.of(user.getId(),user.getSub());
    }

    public User toDomain(UserEntity userEntity) {
        return User.of(userEntity.getId(),userEntity.getSub());
    }
}
