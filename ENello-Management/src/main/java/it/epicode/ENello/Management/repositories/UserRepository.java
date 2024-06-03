package it.epicode.ENello.Management.repositories;



import it.epicode.ENello.Management.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findOneByUsernameAndPassword(String username, String password);
    Optional<UserEntity> findOneByUsername(String username);
}
