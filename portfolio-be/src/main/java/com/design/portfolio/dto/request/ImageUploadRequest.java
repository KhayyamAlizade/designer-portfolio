package com.design.portfolio.dto.request;

import com.design.portfolio.dto.valueobjects.ImageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import javax.validation.constraints.NotNull;;


import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class ImageUploadRequest {
    @NotNull
    public final Long lastImageId;
    @NotNull
    public String title;
    @NotNull
    public final ImageType imageType;
    @NotNull
    public final String height;
    @NotNull
    public String width;
    @NotNull
    public String author;
    @NotNull
    public LocalDateTime publishDate;
    @NotNull
    public  int groupIndex;

}
