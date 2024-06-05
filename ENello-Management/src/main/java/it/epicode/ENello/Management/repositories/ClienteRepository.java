package it.epicode.ENello.Management.repositories;

import it.epicode.ENello.Management.entities.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface ClienteRepository extends JpaRepository<Cliente, Long>, PagingAndSortingRepository<Cliente, Long> {

    @Query("SELECT c FROM Cliente c WHERE c.nomeContatto LIKE %:nome%")
    Page<Cliente> findByNomeContaining(@Param("nome") String nome, Pageable pageable);

    @Query("SELECT c FROM Cliente c WHERE c.fatturatoAnnuale >= :fatturatoAnnuale")
    Page<Cliente> findByFatturatoAnnualeGreaterThanEqual(@Param("fatturatoAnnuale") Double fatturatoAnnuale, Pageable pageable);

    @Query("SELECT c FROM Cliente c WHERE c.dataInserimento >= :dataInserimento")
    Page<Cliente> findByDataInserimentoAfter(@Param("dataInserimento") LocalDate dataInserimento, Pageable pageable);

    @Query("SELECT c FROM Cliente c WHERE c.dataUltimoContatto >= :dataUltimoContatto")
    Page<Cliente> findByDataUltimoContattoAfter(@Param("dataUltimoContatto") LocalDate dataUltimoContatto, Pageable pageable);

    @Query("SELECT c FROM Cliente c WHERE c.sedeLegale.comune.provincia.nome = :provincia")
    Page<Cliente> findByProvinciaSedeLegale(@Param("provincia") String provincia, Pageable pageable);

    @Query("SELECT c FROM Cliente c WHERE " +
            "(:fatturatoAnnuale IS NULL OR c.fatturatoAnnuale >= :fatturatoAnnuale) AND " +
            "(:dataInserimento IS NULL OR c.dataInserimento >= :dataInserimento) AND " +
            "(:dataUltimoContatto IS NULL OR c.dataUltimoContatto >= :dataUltimoContatto) AND " +
            "(:nomeContatto IS NULL OR c.nomeContatto LIKE %:nome%)")
    Page<Cliente> findByFilters(@Param("fatturatoAnnuale") Double fatturatoAnnuale,
                                @Param("dataInserimento") LocalDate dataInserimento,
                                @Param("dataUltimoContatto") LocalDate dataUltimoContatto,
                                @Param("nome") String nome, Pageable pageable);
}
