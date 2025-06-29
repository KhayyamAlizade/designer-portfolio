package com.design.portfolio.controller;

import com.design.portfolio.dto.MediaItemDTO;
import com.design.portfolio.dto.request.ImageUploadRequest;
import com.design.portfolio.service.ImageService;
import org.springframework.beans.factory.annotation.Value;
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
    public ResponseEntity<MediaItemDTO> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam ImageUploadRequest imageUploadRequest) {
           imageService.
        return null;
    }

    @PostMapping("/change")
    public ResponseEntity<MediaItemDTO> chane(@RequestParam("file") MultipartFile file) {
        return null;
    }


    @GetMapping("/images")
    public ResponseEntity<List<MediaItemDTO>> getImage() {
        List<MediaItemDTO> allImages = imageService.getAllImages();
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