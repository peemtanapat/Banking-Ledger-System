package dev.peemtanapat.bankingledgersystem.transfer.core.application.port.out;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.peemtanapat.bankingledgersystem.transfer.core.domain.entity.Transfer;

public interface TransferRepository extends JpaRepository<Transfer, Long> {

}
