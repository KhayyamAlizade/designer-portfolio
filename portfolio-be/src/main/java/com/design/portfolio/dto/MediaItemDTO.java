package com.design.portfolio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class MediaItemDTO {
    private Long id;
    private final String proxy="http://localhost:8080";
    private String fileName;
    private String fileType;
    private String title;
    private String imagePath;

    private int groupIndex;
    private int rowIndex;
    private int columnIndex;
}