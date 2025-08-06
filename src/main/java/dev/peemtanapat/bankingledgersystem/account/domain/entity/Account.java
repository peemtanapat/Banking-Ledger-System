package dev.peemtanapat.bankingledgersystem.account.domain.entity;

import java.math.BigDecimal;

import dev.peemtanapat.bankingledgersystem.account.domain.enums.AccountStatus;
import dev.peemtanapat.bankingledgersystem.account.domain.enums.AccountType;
import dev.peemtanapat.bankingledgersystem.account.domain.enums.Currency;
import dev.peemtanapat.bankingledgersystem.account.domain.valueobject.AccountName;
import dev.peemtanapat.bankingledgersystem.account.domain.valueobject.AccountNumber;
import dev.peemtanapat.bankingledgersystem.account.domain.valueobject.Money;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private AccountNumber accountNumber;

    @Embedded
    private AccountName accountName;

    @Column(length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus = AccountStatus.ACTIVE;

    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Embedded
    private Money balance;

    public Account() {
    }

    public Account(long id) {
        this.id = id;
    }

    public boolean isOwnedBy(String customerId) {
        // TODO: check with the FK customerId
        return true;
    }

    public boolean isActive() {
        return AccountStatus.ACTIVE.equals(this.accountStatus);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountNumber getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(AccountNumber accountNumber) {
        this.accountNumber = accountNumber;
    }

    public AccountName getAccountName() {
        return accountName;
    }

    public void setAccountName(AccountName accountName) {
        this.accountName = accountName;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Money getBalanceObj() {
        return balance;
    }

    public BigDecimal getBalance() {
        return balance.getAmount();
    }

    public Currency getCurrency() {
        return balance.getCurrency();
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = new Money(balance);
    }

    public void setBalance(String balance) {
        this.balance = new Money(balance);
    }

}
