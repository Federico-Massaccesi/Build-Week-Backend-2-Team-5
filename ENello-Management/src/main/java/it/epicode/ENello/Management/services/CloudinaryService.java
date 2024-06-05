package it.epicode.ENello.Management.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
@Slf4j
public class CloudinaryService implements ImageStorageService{

    public static final String CLOUDINARY_URL = System.getenv("CLOUDINARY_URL");

    private final Cloudinary cloudinary;

    @Autowired
    public CloudinaryService() {
        this.cloudinary = new Cloudinary(CLOUDINARY_URL);
        this.cloudinary.config.secure = true;
    }

        @Override
        public String store(byte[] imageBytes, String name) {
            var params = ObjectUtils.asMap("public_id", name);
            try {
                log.info("Uploading image on Cloudinary");
                var info = cloudinary.uploader().upload(imageBytes, params);
                log.info("Image uploaded on Cloudinary");
                return info.get("url").toString();
            } catch (IOException e) {
                log.error("Exception uploading image to Cloudinary", e);
                throw new RuntimeException(e);
            }
        }

        @Override
        public String imageUrl(String name, ImageTransform transform) {
            var url = cloudinary.url().generate(name);
            log.info("{}", url);
            return url;
        }
    }


