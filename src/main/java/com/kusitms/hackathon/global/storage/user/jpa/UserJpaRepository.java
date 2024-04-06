package com.kusitms.hackathon.global.storage.user.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
}
