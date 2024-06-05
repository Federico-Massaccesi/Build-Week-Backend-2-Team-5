package it.epicode.ENello.Management.logRegService;


import it.epicode.ENello.Management.entities.UserEntity;
import it.epicode.ENello.Management.repositories.RoleRepository;
import it.epicode.ENello.Management.repositories.UserRepository;
import it.epicode.ENello.Management.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class UserLogReg {

    @Autowired
    PasswordEncoder encoder;
    @Autowired
    UserRepository userRepo;

    @Autowired
    RoleRepository roleRepo;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwt;

    public UserEntity register(UserEntity user) {
        try {
            var p = encoder.encode(user.getPassword());
            user.setPassword(p);
            return userRepo.save(user);
        } catch (Exception e) {
            throw new RuntimeException("popopopo");
        }
    }

    public Optional<UserEntity> login(String username, String password) {
        try {
            var a = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            a.getAuthorities();
            SecurityContextHolder.getContext().setAuthentication(a);

            var user = userRepo.findOneByUsername(username).orElseThrow();
            user.setToken(jwt.generateToken(a));
            return Optional.of(user);
        } catch (NoSuchElementException | AuthenticationException e) {
            throw new RuntimeException(username);
        }
    }

    public Optional<UserEntity> get(Long id) {
        try {
            return Optional.of(userRepo.findById(id).orElseThrow());
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
