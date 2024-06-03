package it.epicode.ENello.Management.repositories;

import it.epicode.ENello.Management.entities.Indirizzo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IndirizzoRepository extends JpaRepository<Indirizzo, Long>, PagingAndSortingRepository<Indirizzo, Long> {
}
