package it.epicode.ENello.Management.repositories;

import it.epicode.ENello.Management.entities.Fatture;
import it.epicode.ENello.Management.entities.StatoFattura;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface FattureRepository extends JpaRepository<Fatture, Long>, PagingAndSortingRepository<Fatture, Long> {
    @Query("SELECT f FROM Fatture f WHERE f.cliente.id = :clienteId")
    Page<Fatture> findByCliente(@Param("clienteId") Long clienteId, Pageable pageable);

    @Query("SELECT f FROM Fatture f WHERE f.statoFattura = :statoFattura")
    Page<Fatture> findByStatoFattura(@Param("statoFattura") StatoFattura statoFattura, Pageable pageable);

    @Query("SELECT f FROM Fatture f WHERE f.data = :data")
    Page<Fatture> findByData(@Param("data") LocalDate data, Pageable pageable);

    @Query("SELECT f FROM Fatture f WHERE f.cliente.id = :clienteId AND f.data BETWEEN :startDate AND :endDate")
    Page<Fatture> findByClienteAndDateRange(@Param("clienteId") Long clienteId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, Pageable pageable);
}
