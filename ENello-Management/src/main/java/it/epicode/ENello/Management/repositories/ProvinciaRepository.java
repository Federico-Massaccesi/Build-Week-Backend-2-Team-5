package it.epicode.ENello.Management.repositories;

import it.epicode.ENello.Management.entities.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProvinciaRepository extends JpaRepository<Provincia,Long>, PagingAndSortingRepository<Provincia, Long> {
}
