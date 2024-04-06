package com.kusitms.hackathon.global.storage.mining.jpa;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MiningTagEntity {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    private MiningEntity miningEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    private TagEntity tagEntity;
}
