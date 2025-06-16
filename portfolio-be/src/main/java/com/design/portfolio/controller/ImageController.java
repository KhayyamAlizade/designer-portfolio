package com.design.portfolio.controller;

import com.design.portfolio.service.ImageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/media")
@CrossOrigin(origins = "*")
public class ImageController {


    private final ImageService imageService;
    @Value("${file.upload-dir}")
    private String uploadDir;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }


    @GetMapping("/")
    public String listUploadedFiles(Model model) throws Exception {

        model.addAttribute("files", imageService.loadAll().map(
                        path -> MvcUriComponentsBuilder.fromMethodName(ImageController.class,
                                "serveFile", path.getFileName().toString()).build().toUri().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }


    @GetMapping("/images")
    public ResponseEntity<List<Resource>> getImage() {
        try {
            List<Resource> r = new ArrayList<>();
            Path filePath = Paths.get(uploadDir);
            Resource resource = new UrlResource(filePath.toUri());
            r.add(resource);

            if (resource.exists()) {

//                resource.getInputStream().

                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(r);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = imageService.loadAsResource(filename);

        if (file == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

}