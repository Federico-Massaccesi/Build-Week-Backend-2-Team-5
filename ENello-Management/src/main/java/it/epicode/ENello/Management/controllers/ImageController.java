package it.epicode.ENello.Management.controllers;

import it.epicode.ENello.Management.entities.Image;
import it.epicode.ENello.Management.services.ImageService;
import it.epicode.ENello.Management.services.ImageStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping
    public ResponseEntity<Image> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("name") String name) {
        try {
            byte[] imageBytes = file.getBytes();
            Image uploadedImage = imageService.uploadImage(imageBytes, name);
            return new ResponseEntity<>(uploadedImage, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<String> getImageUrl(@RequestParam("name") String name, @RequestParam("width") Integer width, @RequestParam("height") Integer height) {
        ImageStorageService.ImageTransform transform = ImageStorageService.ImageTransform.builder().withWidth(width).withHeight(height).build();
        String url = imageService.getImageUrl(name, transform);
        return new ResponseEntity<>(url, HttpStatus.OK);
    }
}
