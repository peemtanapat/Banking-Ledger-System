package dev.peemtanapat.bankingledgersystem.account.application.dto;

import dev.peemtanapat.bankingledgersystem.account.domain.entity.Account;
import dev.peemtanapat.bankingledgersystem.account.domain.enums.AccountStatus;
import dev.peemtanapat.bankingledgersystem.account.domain.enums.AccountType;
import dev.peemtanapat.bankingledgersystem.account.domain.valueobject.AccountName;
import dev.peemtanapat.bankingledgersystem.account.domain.valueobject.AccountNumber;
import dev.peemtanapat.bankingledgersystem.account.domain.valueobject.Money;
import lombok.Builder;

@Builder
public class AccountDto {

    private final Long accountId;
    private final AccountName accountName;
    private final AccountNumber accountNumber;
    private final AccountType accountType;
    private final AccountStatus accountStatus;
    private final Money balance;

    public static AccountDto from(Account account) {
        return AccountDto.builder()
                .accountId(account.getId())
                .accountNumber(account.getAccountNumber())
                .accountName(account.getAccountName())
                .accountType(account.getAccountType())
                .accountStatus(account.getAccountStatus())
                .balance(account.getBalance())
                .build();
    }

    public Long getAccountId() {
        return accountId;
    }

    public AccountNumber getAccountNumber() {
        return accountNumber;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public Money getBalance() {
        return balance;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public boolean isActive() {
        return AccountStatus.ACTIVE.equals(this.accountStatus);
    }

    public AccountName getAccountName() {
        return accountName;
    }

}
