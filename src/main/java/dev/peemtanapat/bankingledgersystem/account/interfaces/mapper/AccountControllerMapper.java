package dev.peemtanapat.bankingledgersystem.account.interfaces.mapper;

import org.springframework.stereotype.Service;

import dev.peemtanapat.bankingledgersystem.account.application.dto.AccountDto;
import dev.peemtanapat.bankingledgersystem.account.interfaces.dto.request.OpenAccountHttpRequest;
import dev.peemtanapat.bankingledgersystem.account.interfaces.dto.response.AccountHttpResponse;

@Service
public class AccountControllerMapper {

    public AccountHttpResponse toResponse(AccountDto accountDto) {

        return new AccountHttpResponse(
                accountDto.getAccountId(),
                accountDto.getAccountName().getValue(),
                accountDto.getAccountNumber().getValue(),
                accountDto.getAccountType(),
                accountDto.getBalance().getAmount());
    }

    public AccountDto to(OpenAccountHttpRequest accountRequestDto) {
        return null;
    }
}
