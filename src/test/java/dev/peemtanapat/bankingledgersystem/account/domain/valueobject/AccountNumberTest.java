package dev.peemtanapat.bankingledgersystem.account.domain.valueobject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class AccountNumberTest {

    @ParameterizedTest
    @ValueSource(strings = { "0000000000", "3371112223" })
    void validateFormatAccountNumber_whenAllDigitLengthIs10_noThrow(String value) {
        new AccountNumber(value);
    }

    @ParameterizedTest
    @ValueSource(strings = { "1", "000000000", "337111222" })
    void validateFormatAccountNumber_whenAllDigitLengthIsNot10_throwException(String value) {
        IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new AccountNumber(value);
        });

        Assertions.assertEquals(AccountNumber.INVALID_ACC_NUM, ex.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = { "", "A", "A1", "1A", "33711122A" })
    void validateFormatAccountNumber_whenContainNonDigit_throwException(String value) {
        IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new AccountNumber(value);
        });

        Assertions.assertEquals(AccountNumber.INVALID_ACC_NUM, ex.getMessage());
    }
}
