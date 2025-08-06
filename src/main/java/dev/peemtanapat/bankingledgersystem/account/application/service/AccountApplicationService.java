package dev.peemtanapat.bankingledgersystem.account.application.service;

import org.springframework.stereotype.Service;

import dev.peemtanapat.bankingledgersystem.account.application.command.OpenAccountCommand;
import dev.peemtanapat.bankingledgersystem.account.application.dto.AccountDto;
import dev.peemtanapat.bankingledgersystem.account.domain.service.AccountDomainService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountApplicationService {

    private final AccountDomainService accountDomainService;

    public AccountDto openAccount(OpenAccountCommand command) {
        AccountDto savedAccount = accountDomainService.openSavingAccount(command.accountName(), command.balance());

        return savedAccount;
    }
}
