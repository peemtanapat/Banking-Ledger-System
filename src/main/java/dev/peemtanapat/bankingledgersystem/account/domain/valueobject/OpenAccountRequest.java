package dev.peemtanapat.bankingledgersystem.account.domain.valueobject;

import dev.peemtanapat.bankingledgersystem.account.domain.enums.AccountType;

public record OpenAccountRequest(
        AccountName accountName,
        AccountType accountType,
        Money balance) {
}