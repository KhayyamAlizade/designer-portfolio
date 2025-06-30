package com.design.portfolio.service;

import com.design.portfolio.dto.MediaItemDTO;
import com.design.portfolio.dto.request.ImageUploadRequest;
import com.design.portfolio.dto.response.ImageResponse;
import com.design.portfolio.dto.valueobjects.ImageType;
import com.design.portfolio.entity.MediaItemEntity;
import com.design.portfolio.mapper.ImageMapper;
import com.design.portfolio.repository.ImageRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Validated
@Slf4j
@Service
public class ImageService {

    @Value("${file.upload-dir}")
    private Path mediaPath;
    private final ImageMapper imageMapper;
    private final ImageRepository imageRepository;


    public ImageService(ImageMapper imageMapper, ImageRepository imageRepository) {
        this.imageMapper = imageMapper;
        this.imageRepository = imageRepository;
    }



    public ImageResponse upload(MultipartFile meta, ImageUploadRequest request) throws Exception {

        try {
            if (meta.isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }
            Path destinationFile = this.mediaPath.resolve(
                            Paths.get(meta.getOriginalFilename()))
                    .normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(this.mediaPath.toAbsolutePath())) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file outside current directory.");
            }
            try (InputStream inputStream = meta.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file.", e);
        }

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
