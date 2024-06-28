package it.epicode.ENello.Management.controllers;

import com.cloudinary.Cloudinary;
import it.epicode.ENello.Management.entities.UserEntity;
import it.epicode.ENello.Management.logRegService.UserLogReg;
import it.epicode.ENello.Management.repositories.UserRepository;
import it.epicode.ENello.Management.services.UserService;
import it.epicode.ENello.Management.validators.LogValidation;
import it.epicode.ENello.Management.validators.RegValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.List;
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
            var uploadResult = cloudinary.uploader().upload(file.getBytes(),
                    com.cloudinary.utils.ObjectUtils.asMap("public_id", username + "_avatar"));

            String url = uploadResult.get("url").toString();

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

    @Autowired
    UserService svc;


    @Autowired
    UserLogReg logReg;
    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody @Validated RegValidation userValid,
                                      BindingResult validation,
                                      UriComponentsBuilder uri){
        if(validation.hasErrors()) {
            throw new RuntimeException("no");
        }
        var newUser = logReg.register(UserEntity.builder()
                .withEmail(userValid.email())
                .withUsername(userValid.username())
                .withPassword(userValid.password())
                .withRoles(userValid.roles())
                .build());

        var headers = new HttpHeaders();

        headers.add("Location",uri.path("/api/users/{id}").buildAndExpand(newUser.getId()).toString());

        return new ResponseEntity<>(newUser,headers, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Validated LogValidation model, //
                                   BindingResult validation) {
        if (validation.hasErrors()) {
            throw new RuntimeException("no");
        }

        return new ResponseEntity<>(logReg.login(model.username(), model.password()).orElseThrow(),
                HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<UserEntity>> getUsers(){

        var users = svc.getAllUsers();

        return new ResponseEntity<>(users, HttpStatus.FOUND);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Long id){
        var foundedUser = svc.getUserById(id);
        return new ResponseEntity<>(foundedUser, HttpStatus.FOUND);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserEntity> getUserByUsername(@PathVariable String username) {
        Optional<UserEntity> userOptional = userRepository.findOneByUsername(username);
        return userOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UserEntity> deleteUser(@PathVariable Long id){

        var user=svc.delete(id);

        return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
    }

    @Autowired
    private UserService userService;

    @GetMapping("/send-email")
    public ResponseEntity<String> sendTestEmail(@RequestParam String email) {
        userService.sendMailRegistrazione(email);
        return ResponseEntity.ok("Email inviata con successo!");
    }
}
