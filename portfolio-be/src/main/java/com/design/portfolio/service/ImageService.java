package com.design.portfolio.service;

import com.design.portfolio.dto.MediaItemDTO;
import com.design.portfolio.dto.request.ImageUploadRequest;
import com.design.portfolio.dto.response.ImageResponse;
import com.design.portfolio.dto.valueobjects.ImageType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Stream;

@Validated
@Slf4j
@Service
public class ImageService {

    private final Path rootLocation;

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


    }

    ;


    public List<MediaItemDTO> getAllImages() throws IOException {
        List<MediaItemDTO> images = new ArrayList<>();
        File mediaFolder = new ClassPathResource("static/media").getFile();

        File[] files = mediaFolder.listFiles((dir, name) ->
                name.toLowerCase().endsWith(".png") ||
                        name.toLowerCase().endsWith(".jpg") ||
                        name.toLowerCase().endsWith(".jpeg"));

        if (files != null) {
            for (File file : files) {
                String fileName = file.getName();
                String fileType = getFileExtension(fileName);
                BufferedImage img = ImageIO.read(file);
                if (img == null) continue;

                int width = img.getWidth();
                int height = img.getHeight();
                String ratioType = determineRatioType(width, height);
                String base64 = encodeToBase64(file, fileType);

                images.add(MediaItemDTO.builder().

                        fileName(fileName).
                        fileType(fileType).
                        width(width).
                        height(height).
                        ratioType(ratioType).
                        base64(base64).build());
            }
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

    private String encodeToBase64(File file, String fileType) throws IOException {
        try (InputStream is = new FileInputStream(file)) {
            byte[] bytes = is.readAllBytes();
            String base64 = Base64.getEncoder().encodeToString(bytes);
            return "data:image/" + fileType + ";base64," + base64;
        }
    }


    private ImageType checkImage(ImageUploadRequest request) {

        return null;
    }
}
