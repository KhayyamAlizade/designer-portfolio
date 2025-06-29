package com.design.portfolio.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ImageResponse {
    public String imageId;
    public String imageName;
    public String imageTitle;
    public String imageExtension;
    public String imagePath;

    private int groupIndex;
    private int rowIndex;
    private int columnIndex;
}
