package com.design.portfolio.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

;

@Getter
@Builder
@AllArgsConstructor
public class ImageUploadRequest {
    @NotNull
    public final Long lastImageId;
    @NotNull
    public String title;
    @NotNull
    public  int groupIndex;
    @NotNull
    public  int rowIndex;
    @NotNull
    public  int columnIndex;

}
