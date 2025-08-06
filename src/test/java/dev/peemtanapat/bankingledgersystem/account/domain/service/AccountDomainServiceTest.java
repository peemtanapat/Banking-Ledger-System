package dev.peemtanapat.bankingledgersystem.account.domain.service;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.peemtanapat.bankingledgersystem.account.application.dto.AccountDto;
import dev.peemtanapat.bankingledgersystem.account.domain.entity.Account;
import dev.peemtanapat.bankingledgersystem.account.domain.enums.AccountStatus;
import dev.peemtanapat.bankingledgersystem.account.domain.enums.AccountType;
import dev.peemtanapat.bankingledgersystem.account.domain.repository.AccountRepository;
import dev.peemtanapat.bankingledgersystem.account.domain.valueobject.AccountName;
import dev.peemtanapat.bankingledgersystem.account.domain.valueobject.Money;

@ExtendWith(MockitoExtension.class)
class AccountDomainServiceTest {

    @InjectMocks
    private AccountDomainService accountDomainService;

    @Mock
    private AccountRepository accountRepository;

    @Test
    void openAccount_whenAllDataIsValid_beSuccess() {
        // arrange
        Account mockAccount = new Account();
        mockAccount.setAccountType(AccountType.SAVINGS);
        mockAccount.setAccountStatus(AccountStatus.ACTIVE);
        Mockito.when(accountRepository.save(Mockito.any())).thenReturn(mockAccount);

        // action
        AccountDto createdAccount = accountDomainService.openSavingAccount(new AccountName("TANAPAT CHOOCHOT"),
                new Money(new BigDecimal(500.55)));

        // assert
        Assertions.assertTrue(createdAccount.isActive());
    }

    @Test
    void openAccount_whenAccountNameIsBlank_beSuccess() {
        // action & assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            accountDomainService
                    .openSavingAccount(null, new Money(new BigDecimal(500.55)));
        });
    }

}
