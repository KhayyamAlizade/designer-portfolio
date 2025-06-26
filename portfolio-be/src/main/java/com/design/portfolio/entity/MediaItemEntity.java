package com.design.portfolio.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MediaItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String height;
    private String width;
    private String author;
    private String publishedDate;
    private String imagePath;

    private int groupIndex;
    private int rowIndex;
    private int columnIndex;
}
