package com.design.portfolio.service;

import com.design.portfolio.dto.request.ImageUploadRequest;
import com.design.portfolio.dto.response.ImageResponse;
import com.design.portfolio.dto.valueobjects.ImageType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

@Validated
@Slf4j
@Service
public class ImageService {

    private  final Path rootLocation;

    public ImageService(@Value("${file.upload-dir}") String uploadDir) {
        this.rootLocation = Path.of(uploadDir);
    }

//    private final ImageRepository imageRepository;
//
//    ImageService(ImageRepository imageRepository) {
//        this.imageRepository = imageRepository;
//    }

    public ImageResponse upload(MultipartFile meta, ImageUploadRequest request) throws Exception {

        String heigh = request.getHeigh();
        String width = request.getWidth();
        String fileName = request.getFileName();
        String gridId = request.getGridId();
        ImageType imageType = request.getImageType();


        Double heightDouble = Double.valueOf(heigh);
        Double widthDouble = Double.valueOf(width);

        double ratio = widthDouble / heightDouble;

        if (ratio >= 1.25 && imageType.equals(ImageType.HORIZONTAL)) {
            throw new Exception("Shekil uygun statusla gonderilmeyib");
        } else if (ratio <= 0.85 && imageType.equals(ImageType.VERTICAL)) {
            throw new Exception("Shekil uygun statusla gonderilmeyib");
        }

        return null;


    };




    public Stream<Path> loadAll() throws Exception {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        }
        catch (IOException e) {
            throw new Exception("Failed to read stored files", e);
        }

    }



    private ImageType checkImage(ImageUploadRequest request) {

        return null;
    }
}
