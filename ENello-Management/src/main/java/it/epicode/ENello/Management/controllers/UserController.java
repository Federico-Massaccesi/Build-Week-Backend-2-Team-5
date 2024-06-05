package it.epicode.ENello.Management.controllers;

import com.cloudinary.Cloudinary;
import it.epicode.ENello.Management.entities.UserEntity;
import it.epicode.ENello.Management.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/{username}/avatar")
    public ResponseEntity<String> uploadAvatar(@PathVariable String username, @RequestParam("file") MultipartFile file) {
        try {
            // Carica l'immagine su Cloudinary
            var uploadResult = cloudinary.uploader().upload(file.getBytes(),
                    com.cloudinary.utils.ObjectUtils.asMap("public_id", username + "_avatar"));

            // Recupera l'URL dell'immagine
            String url = uploadResult.get("url").toString();

            // Aggiorna l'utente con l'URL dell'immagine avatar
            Optional<UserEntity> userOptional = userRepository.findOneByUsername(username);
            if (userOptional.isPresent()) {
                UserEntity user = userOptional.get();
                user.setAvatarImg(url);
                userRepository.save(user);
                return ResponseEntity.ok(url);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload avatar");
        }
    }

    @GetMapping("/{username}/avatar")
    public ResponseEntity<String> getUserAvatar(@PathVariable String username) {
        Optional<UserEntity> user = userRepository.findOneByUsername(username);
        if (user.isPresent() && user.get().getAvatarImg() != null) {
            return ResponseEntity.ok(user.get().getAvatarImg());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Avatar not found");
        }
    }
}
