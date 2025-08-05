package dev.peemtanapat.bankingledgersystem.account.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.peemtanapat.bankingledgersystem.account.domain.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
