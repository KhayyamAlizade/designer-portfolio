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
    private String fileName;
    private String fileType;
    private int width;
    private int height;
    private String ratioType;
    private String base64;
}