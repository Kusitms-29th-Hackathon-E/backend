package com.kusitms.hackathon.global.storage.mining.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagJpaRepository extends JpaRepository<TagEntity, Long> {



    @Query("""
            select t
            from TagEntity t
            where t.description in :descriptions
            """)
    List<TagEntity> findAllByDescriptionsIn(List<String> descriptions);
}
