package com.design.portfolio.controller;

import com.design.portfolio.dto.MediaItemDTO;
import com.design.portfolio.dto.request.ImageUploadRequest;
import com.design.portfolio.dto.response.ImageResponse;
import com.design.portfolio.dto.valueobjects.PageType;
import com.design.portfolio.exceptions.ImagesNotFoundException;
import com.design.portfolio.service.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/media")
@CrossOrigin(origins = "*")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }



    @PostMapping("/upload")
    public ResponseEntity<MediaItemDTO> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam ImageUploadRequest imageUploadRequest) throws Exception {
        imageService.uploadImage(file, imageUploadRequest);
        return null;
    }

    @PostMapping("/change")
    public ResponseEntity<MediaItemDTO> chane(@RequestParam("file") MultipartFile file) {
        return null;
    }


    @GetMapping("/home/images")
    public ResponseEntity<List<MediaItemDTO>> getImage() throws ImagesNotFoundException {
        List<MediaItemDTO> allImages = imageService.getAllHomeImages();
        return ResponseEntity.ok(allImages);
    }

//    @GetMapping("/")
//    public String listUploadedFiles(Model model) throws Exception {
//
//        model.addAttribute("files", imageService.loadAll().map(
//                        path -> MvcUriComponentsBuilder.fromMethodName(ImageController.class,
//                                "serveFile", path.getFileName().toString()).build().toUri().toString())
//                .collect(Collectors.toList()));
//
//        return "uploadForm";
//    }



}