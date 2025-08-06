package dev.peemtanapat.bankingledgersystem.account.interfaces.controller;

import org.springframework.web.bind.annotation.RestController;

import dev.peemtanapat.bankingledgersystem.account.application.command.OpenAccountCommand;
import dev.peemtanapat.bankingledgersystem.account.application.dto.AccountDto;
import dev.peemtanapat.bankingledgersystem.account.application.service.AccountApplicationService;
import dev.peemtanapat.bankingledgersystem.account.domain.enums.AccountType;
import dev.peemtanapat.bankingledgersystem.account.domain.valueobject.AccountName;
import dev.peemtanapat.bankingledgersystem.account.domain.valueobject.Money;
import dev.peemtanapat.bankingledgersystem.account.interfaces.dto.request.OpenAccountHttpRequest;
import dev.peemtanapat.bankingledgersystem.account.interfaces.dto.response.AccountHttpResponse;
import dev.peemtanapat.bankingledgersystem.account.interfaces.mapper.AccountControllerMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
@RestController
public class AccountController {

    private final AccountApplicationService accountApplicationService;
    private final AccountControllerMapper accountControllerMapper;

    @PostMapping("/savings")
    public AccountHttpResponse openSavingsAccount(@RequestBody @Valid OpenAccountHttpRequest request) {
        OpenAccountCommand command = new OpenAccountCommand(
                new AccountName(request.accountName()),
                new Money(request.initialBalance()),
                AccountType.SAVINGS);

        AccountDto account = accountApplicationService.openAccount(command);

        return accountControllerMapper.toResponse(account);
    }

}
