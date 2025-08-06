package dev.peemtanapat.bankingledgersystem.account.domain.valueobject;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class AccountNumber {

    protected final static String INVALID_ACC_NUM = "Invalid account number format: must be 10 length of digit";

    @Column(name = "account_number", unique = true, length = 10)
    private String value;

    protected AccountNumber() {
    }

    public AccountNumber(String value) {
        validateFormat(value);
        this.value = value;
    }

    private void validateFormat(String value) {
        if (value == null || !value.matches("\\d{10}")) {
            throw new IllegalArgumentException(INVALID_ACC_NUM);
        }
    }

    public String getValue() {
        return value;
    }
}
