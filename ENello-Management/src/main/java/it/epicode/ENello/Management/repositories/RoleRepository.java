package it.epicode.ENello.Management.repositories;

import it.epicode.ENello.Management.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity,Long> {
}
