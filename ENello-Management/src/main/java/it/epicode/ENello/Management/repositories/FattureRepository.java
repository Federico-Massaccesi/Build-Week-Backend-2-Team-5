package it.epicode.ENello.Management.repositories;

import it.epicode.ENello.Management.entities.Fatture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FattureRepository extends JpaRepository<Fatture, Long>, PagingAndSortingRepository<Fatture, Long>  {
}
