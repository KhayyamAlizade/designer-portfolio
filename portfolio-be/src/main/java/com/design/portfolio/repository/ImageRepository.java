package com.design.portfolio.repository;

import com.design.portfolio.dto.valueobjects.PageType;
import com.design.portfolio.entity.MediaItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<MediaItemEntity,Long> {

    MediaItemEntity findMediaItemEntityById(Long id);
}
