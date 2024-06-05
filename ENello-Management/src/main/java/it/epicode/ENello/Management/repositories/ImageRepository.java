package it.epicode.ENello.Management.repositories;

import it.epicode.ENello.Management.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Long> {
}
