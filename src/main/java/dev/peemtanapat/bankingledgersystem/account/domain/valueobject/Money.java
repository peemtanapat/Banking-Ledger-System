package dev.peemtanapat.bankingledgersystem.account.domain.valueobject;

import java.math.BigDecimal;

import dev.peemtanapat.bankingledgersystem.account.domain.enums.Currency;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public class Money {

    protected final static String INVALID_AMOUNT = "Invalid amount: must be greater than or equal zero";

    @Column(length = 3, nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currency = Currency.THB;

    @Column(name = "balance", nullable = false)
    private BigDecimal amount;

    protected Money() {
    }

    public Money(BigDecimal amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    public Money(Currency currency, BigDecimal amount) {
        this(amount);
        this.currency = currency;
    }

    private void validateAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(INVALID_AMOUNT);
        }
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
