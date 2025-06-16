package com.design.portfolio.dto.request;

import com.design.portfolio.dto.valueobjects.ImageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import javax.validation.constraints.NotNull;;



import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class ImageUploadRequest {
    @NotNull
    public final UUID imageId;
    @NotNull
    public final ImageType imageType;
    @NotNull
    public final String heigh;
    @NotNull
    public String width;
    @NotNull
    public String fileName;
    @NotNull
    public String gridId;

}
