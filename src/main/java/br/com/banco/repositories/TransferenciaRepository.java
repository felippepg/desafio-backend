package br.com.banco.repositories;

import br.com.banco.entities.Conta;
import br.com.banco.entities.Transferencia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
    Page<Transferencia> findByConta(Conta conta, Pageable pageable);
    List<Transferencia> findByConta(Conta conta);
    Page<Transferencia> findByDataTransferenciaBetween(LocalDateTime inicial, LocalDateTime dataFinal, Pageable pageable);
    List<Transferencia> findByDataTransferenciaBetween(LocalDateTime inicial, LocalDateTime dataFinal);



}
