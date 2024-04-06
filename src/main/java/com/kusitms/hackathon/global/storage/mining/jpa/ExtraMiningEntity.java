package com.kusitms.hackathon.global.storage.mining.jpa;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Entity
public class ExtraMiningEntity {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate createdDate;
    private String response;

    @ManyToOne(fetch = FetchType.LAZY)
    private MiningEntity miningEntity;
}
