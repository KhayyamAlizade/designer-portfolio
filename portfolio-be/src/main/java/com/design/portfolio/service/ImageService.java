package com.design.portfolio.service;

import com.design.portfolio.dto.MediaItemDTO;
import com.design.portfolio.dto.request.ImageUploadRequest;
import com.design.portfolio.dto.valueobjects.PageType;
import com.design.portfolio.entity.MediaItemEntity;
import com.design.portfolio.exceptions.ImagesNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {


    void uploadImage(MultipartFile file, ImageUploadRequest id) throws ImagesNotFoundException;

    MediaItemEntity findImageById(Long imageId);

    List<MediaItemDTO> getAllHomeImages() throws ImagesNotFoundException;

    void updateImageTitle(Long id, String newTitle);
}
