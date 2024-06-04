package it.epicode.ENello.Management.repositories;

import it.epicode.ENello.Management.entities.Cliente;
import it.epicode.ENello.Management.entities.Comune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ComuneRepository extends JpaRepository<Comune,Long>, PagingAndSortingRepository<Comune, Long> {

     Optional<Comune> findByNome(String name);
}
