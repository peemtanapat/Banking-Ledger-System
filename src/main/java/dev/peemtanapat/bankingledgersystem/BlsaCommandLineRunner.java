package dev.peemtanapat.bankingledgersystem;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import dev.peemtanapat.bankingledgersystem.account.domain.entity.Account;
import dev.peemtanapat.bankingledgersystem.account.domain.enums.AccountType;
import dev.peemtanapat.bankingledgersystem.account.domain.repository.AccountRepository;
import dev.peemtanapat.bankingledgersystem.account.domain.valueobject.AccountName;
import dev.peemtanapat.bankingledgersystem.account.domain.valueobject.AccountNumber;
import dev.peemtanapat.bankingledgersystem.account.domain.valueobject.Money;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BlsaCommandLineRunner implements CommandLineRunner {

    private final AccountRepository accountRepository;

    @Override
    public void run(String... args) throws Exception {
        Account firstAccount = new Account();
        firstAccount.setAccountNumber(new AccountNumber("1111111111"));
        firstAccount.setAccountName(new AccountName("TANAPAT CHOOCHOT"));
        firstAccount.setAccountType(AccountType.SAVINGS);
        firstAccount.setBalance(new Money(new BigDecimal(500.55)));

        accountRepository.save(firstAccount);
    }

}
