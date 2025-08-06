package dev.peemtanapat.bankingledgersystem.account.domain.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import dev.peemtanapat.bankingledgersystem.account.application.dto.AccountDto;
import dev.peemtanapat.bankingledgersystem.account.domain.entity.Account;
import dev.peemtanapat.bankingledgersystem.account.domain.enums.AccountType;
import dev.peemtanapat.bankingledgersystem.account.domain.repository.AccountRepository;
import dev.peemtanapat.bankingledgersystem.account.domain.valueobject.AccountName;
import dev.peemtanapat.bankingledgersystem.account.domain.valueobject.AccountNumber;
import dev.peemtanapat.bankingledgersystem.account.domain.valueobject.Money;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountDomainService {

    private final AccountRepository accountRepository;

    public AccountDto openSavingAccount(AccountName accountName, Money initialBalance) {
        if (accountName == null) {
            throw new IllegalArgumentException(AccountName.INVALID_ACC_NAME);
        }
        if (initialBalance.getAmount().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(Money.INVALID_AMOUNT);
        }

        Account account = new Account();
        account.setAccountName(accountName);
        account.setAccountType(AccountType.SAVINGS);
        // TODO: create generate 10-digit account number
        account.setAccountNumber(generateUniqueAccountNumber());
        account.setBalance(initialBalance);

        Account savedAccount = accountRepository.save(account);

        return AccountDto.from(savedAccount);
    }

    public AccountNumber generateUniqueAccountNumber() {
        return new AccountNumber("9999999999");
    }

}
