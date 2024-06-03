package it.epicode.ENello.Management.repositories;

import it.epicode.ENello.Management.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClienteRepository  extends JpaRepository<Cliente, Long>, PagingAndSortingRepository<Cliente, Long>{
}
