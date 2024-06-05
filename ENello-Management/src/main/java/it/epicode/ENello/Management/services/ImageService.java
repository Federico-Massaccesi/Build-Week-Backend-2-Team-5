package it.epicode.ENello.Management.services;

import it.epicode.ENello.Management.entities.Image;
import it.epicode.ENello.Management.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ImageService {

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private ImageRepository imageRepository;

    @Transactional
    public Image uploadImage(byte[] imageBytes, String name) {
        String url = cloudinaryService.store(imageBytes, name);
        Image image = Image.builder()
                .withName(name)
                .withUrl(url)
                .build();
        return imageRepository.save(image);
    }

    public String getImageUrl(String name, ImageStorageService.ImageTransform transform) {
        return cloudinaryService.imageUrl(name, transform);
    }
}
