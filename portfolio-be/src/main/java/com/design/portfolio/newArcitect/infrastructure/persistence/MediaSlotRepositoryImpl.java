package com.design.portfolio.newArcitect.infrastructure.persistence;

import com.design.portfolio.newArcitect.domain.media.MediaSlot;
import com.design.portfolio.newArcitect.domain.media.MediaSlotRepository;
import com.design.portfolio.newArcitect.domain.media.PageType;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class MediaSlotRepositoryImpl implements MediaSlotRepository {

  private final JpaMediaSlotRepository jpaRepository;

  public MediaSlotRepositoryImpl(JpaMediaSlotRepository jpaRepository) {
    this.jpaRepository = jpaRepository;
  }

  @Override
  public List<MediaSlot> findByPageType(PageType pageType) {

    return jpaRepository.findByPageType(pageType.name())
        .stream()
        .map(entity ->

        ).collect(Collectors.toList());
  }
}
