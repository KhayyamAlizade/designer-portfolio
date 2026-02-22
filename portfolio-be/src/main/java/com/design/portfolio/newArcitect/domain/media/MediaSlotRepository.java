package com.design.portfolio.newArcitect.domain.media;

import java.util.List;

public interface  MediaSlotRepository {

    List<MediaSlot> findByPageType(PageType pageType);
}
