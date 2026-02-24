package com.design.portfolio.newArcitect.domain.media;

import lombok.Builder;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.With;

@Setter
@Getter
@With
@Builder
public record MediaSlot() {

  static Long id;
  static PageType pageType;
  static int groupIndex;
  static int rowIndex;
  static int columnIndex;
  static String title;
  static String fileName;
  static String filePath;
  static LocalDateTime updatedAt;

}
