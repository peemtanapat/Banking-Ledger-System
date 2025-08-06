package dev.peemtanapat.bankingledgersystem.account.interfaces.dto.response;

import java.math.BigDecimal;

import dev.peemtanapat.bankingledgersystem.account.domain.enums.AccountType;

public record AccountHttpResponse(
                Long accountId,
                String accountName,
                String accountNumber,
                AccountType accountType,
                BigDecimal balance) {
}
