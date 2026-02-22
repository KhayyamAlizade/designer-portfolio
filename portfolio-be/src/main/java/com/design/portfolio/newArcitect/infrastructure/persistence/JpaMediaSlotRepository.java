package com.design.portfolio.newArcitect.infrastructure.persistence;

import com.design.portfolio.newArcitect.domain.media.MediaSlot;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class JpaMediaSlotRepository implements JpaRepository<MediaSlot, Long> {
}
