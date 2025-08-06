package dev.peemtanapat.bankingledgersystem.account.domain.valueobject;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class AccountName {

    public final static String INVALID_ACC_NAME = "Invalid account name: must not be null or blank";
    public final static String INVALID_ACC_NAME_LENGTH = "Invalid account name: must not be exceed 256 characters";

    @Column(name = "account_name")
    private String value;

    protected AccountName() {
    }

    public AccountName(String value) {
        validateAccountName(value);
        validateAccountNameLength(value);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void validateAccountName(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(INVALID_ACC_NAME);
        }
    }

    public void validateAccountNameLength(String value) {
        if (value.length() > 256) {
            throw new IllegalArgumentException(INVALID_ACC_NAME_LENGTH);
        }
    }
}
