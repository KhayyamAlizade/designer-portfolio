package com.design.portfolio.service.impl;

import com.design.portfolio.dto.MediaItemDTO;
import com.design.portfolio.dto.request.ImageUploadRequest;
import com.design.portfolio.entity.MediaItemEntity;
import com.design.portfolio.exceptions.ImagesNotFoundException;
import com.design.portfolio.mapper.ImageMapper;
import com.design.portfolio.repository.ImageRepository;
import com.design.portfolio.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Validated
@Slf4j
@Service
public class ImageServiceIml implements ImageService {

    @Value("${file.upload-dir}")
    private Path mediaPath;
    private final ImageMapper imageMapper;
    private final ImageRepository imageRepository;


    public ImageServiceIml(ImageMapper imageMapper, ImageRepository imageRepository) {
        this.imageMapper = imageMapper;
        this.imageRepository = imageRepository;
    }


    @Override
    public void uploadImage(MultipartFile file, ImageUploadRequest request) throws ImagesNotFoundException {
//        MediaItemEntity entity = (MediaItemEntity) imageRepository.findMediaItemEntityByPageTypeAndId(page, id)
//                .stream()
//                .findFirst()
//                .orElseThrow(() -> new ImagesNotFoundException("File not found.Download Fail!"));
//
//        Path uploadDir = Paths.get(mediaPath.toUri());
//        Path oldFilePath = Paths.get(entity.getImagePath(), entity.getImageName());
//        Path newFilePath = uploadDir.resolve(Objects.requireNonNull(file.getOriginalFilename()));
//
//        try {
//
////            Util.deleteFile(oldFilePath.toFile());
//
//
//            Files.createDirectories(uploadDir);
//
//
//            try (InputStream inputStream = file.getInputStream()) {
//                Files.copy(inputStream, newFilePath, StandardCopyOption.REPLACE_EXISTING);
//            }
//
//            log.info("File uploaded: {}", newFilePath);
//        } catch (IOException ex) {
//            log.error("uploadImage error", ex);
//            throw new ImagesNotFoundException("Photo not downloaded: " + ex.getMessage());
//        }
//
//
//        entity.setImageName(file.getOriginalFilename());
//        entity.setPublishedDate(LocalDate.now().toString());
//        entity.setImagePath(uploadDir.toString());
//
//        imageRepository.save(entity);
    }


    @Override
    public MediaItemEntity findImageById(Long imageId) {
        return fetchMediaItemById(imageId);

    }


    @Override
    public List<MediaItemDTO> getAllHomeImages() throws ImagesNotFoundException {
        List<MediaItemEntity> all = imageRepository.findAll();
        List<MediaItemDTO> images = new ArrayList<>();
        if (!all.isEmpty()) {
            all.stream().map(imageMapper::toMediaDTO).forEach(images::add);
        } else {
            log.error("ActionFound.getAllImages.images is empty");
            throw new ImagesNotFoundException("Shekiller yuklenerken xeta vbas verdi");
        }
        return images;
    }

    @Override
    public void updateImageTitle(Long id, String newTitle) {
        MediaItemEntity imageById = findImageById(id);
        imageById.setTitle(newTitle);
        imageRepository.save(imageById);
    }


    public MediaItemEntity fetchMediaItemById(Long id) {
        MediaItemEntity mediaItemEntityById = imageRepository.findMediaItemEntityById(id);
        if (mediaItemEntityById == null) {
            throw new RuntimeException("MediaItemEntity not found");
        } else return mediaItemEntityById;
    }
}
