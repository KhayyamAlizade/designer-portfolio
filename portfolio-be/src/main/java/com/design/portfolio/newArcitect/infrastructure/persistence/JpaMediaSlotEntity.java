package com.design.portfolio.newArcitect.infrastructure.persistence;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "media_slot")
public class JpaMediaSlotEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private String pageType;

  private int groupIndex;
  private int rowIndex;
  private int columnIndex;

  private String title;
  private String fileName;
  private String filePath;

}
