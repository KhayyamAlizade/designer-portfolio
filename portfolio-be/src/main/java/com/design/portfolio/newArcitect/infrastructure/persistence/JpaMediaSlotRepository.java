package com.design.portfolio.newArcitect.infrastructure.persistence;

import com.design.portfolio.newArcitect.domain.media.MediaSlot;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMediaSlotRepository extends JpaRepository<MediaSlot, Long> {

  List<JpaMediaSlotEntity> findByPageType(String pageType);
}
