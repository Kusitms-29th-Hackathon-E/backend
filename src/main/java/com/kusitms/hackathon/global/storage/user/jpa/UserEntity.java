package com.kusitms.hackathon.global.storage.user.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserEntity {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
