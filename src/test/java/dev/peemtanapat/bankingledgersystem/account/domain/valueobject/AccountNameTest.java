package dev.peemtanapat.bankingledgersystem.account.domain.valueobject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class AccountNameTest {

    @ParameterizedTest
    @ValueSource(strings = { "a", "a b", "david dell" })
    void validateAccountName_whenThereIsAtLeastOneCharacter_shouldSuccess(String value) {
        new AccountName(value);
    }

    @ParameterizedTest
    @ValueSource(strings = { "", " ", "      " })
    void validateAccountName_whenThereIsAtLeastOneCharacter_throwException(String value) {
        IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new AccountName(value);
        });

        Assertions.assertEquals(AccountName.INVALID_ACC_NAME, ex.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, s" })
    void validateAccountName_whenExceed256Characters_throwException(String value) {
        IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new AccountName(value);
        });

        Assertions.assertEquals(AccountName.INVALID_ACC_NAME_LENGTH, ex.getMessage());
    }
}
