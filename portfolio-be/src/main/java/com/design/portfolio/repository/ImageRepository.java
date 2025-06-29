package com.design.portfolio.repository;

import com.design.portfolio.entity.MediaItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<MediaItemEntity,Long> {
}
