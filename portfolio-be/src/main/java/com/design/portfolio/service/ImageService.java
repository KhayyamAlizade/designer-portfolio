package com.design.portfolio.service;

import com.design.portfolio.dto.MediaItemDTO;
import com.design.portfolio.dto.request.ImageUploadRequest;
import com.design.portfolio.dto.response.ImageResponse;
import com.design.portfolio.dto.valueobjects.ImageType;
import com.design.portfolio.entity.MediaItemEntity;
import com.design.portfolio.mapper.ImageMapper;
import com.design.portfolio.repository.ImageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Validated
@Slf4j
@Service
public class ImageService {

    private final ImageMapper imageMapper;
    private final ImageRepository imageRepository;


    public ImageService(ImageMapper imageMapper, ImageRepository imageRepository) {
        this.imageMapper = imageMapper;
        this.imageRepository = imageRepository;
    }



    public ImageResponse upload(MultipartFile meta, ImageUploadRequest request) throws Exception {

        meta.

        double ratio = widthDouble / heightDouble;

        if (ratio >= 1.25 && imageType.equals(ImageType.HORIZONTAL)) {
            throw new Exception("Shekil uygun statusla gonderilmeyib");
        } else if (ratio <= 0.85 && imageType.equals(ImageType.VERTICAL)) {
            throw new Exception("Shekil uygun statusla gonderilmeyib");
        }

        return null;


    }

    ;


    public List<MediaItemDTO> getAllImages(){
        List<MediaItemEntity> all = imageRepository.findAll();
        List<MediaItemDTO> images = new ArrayList<>();
        if (!all.isEmpty()) {
            all.stream().map(imageMapper::toDTO).forEach(images::add);
        }
        return images;
    }




private String getFileExtension(String fileName) {
    int lastDot = fileName.lastIndexOf('.');
    return lastDot != -1 ? fileName.substring(lastDot + 1).toLowerCase() : "";
}

private String determineRatioType(int width, int height) {
    if (height > width)
        return ImageType.VERTICAL.name();
    else if (width > height)
        return ImageType.HORIZONTAL.name();
    else
        return ImageType.NORMAL.name();
}


private ImageType checkImage(ImageUploadRequest request) {

    return null;
}
}
