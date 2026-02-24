package com.design.portfolio.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "media_item_entity")
public class MediaItemEntity {
    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private String pageType;
    private String imageName;
    private String title;
    private String author;
    private String publishedDate;
    private String imagePath;

    private int groupIndex;
    private int rowIndex;
    private int columnIndex;


}
