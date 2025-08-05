package dev.peemtanapat.bankingledgersystem.account.domain.valueobject;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class AccountName {

    @Column(name = "account_name")
    private String value;

    protected AccountName() {
    }

    public AccountName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    // TODO: implement validator
}
