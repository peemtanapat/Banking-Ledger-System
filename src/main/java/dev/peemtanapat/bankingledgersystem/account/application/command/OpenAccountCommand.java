package dev.peemtanapat.bankingledgersystem.account.application.command;

import dev.peemtanapat.bankingledgersystem.account.domain.enums.AccountType;
import dev.peemtanapat.bankingledgersystem.account.domain.valueobject.AccountName;
import dev.peemtanapat.bankingledgersystem.account.domain.valueobject.Money;

public record OpenAccountCommand(
                AccountName accountName,
                Money balance,
                AccountType accountType) {
}