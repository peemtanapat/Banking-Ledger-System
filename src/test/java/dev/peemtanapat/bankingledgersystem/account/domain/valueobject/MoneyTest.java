package dev.peemtanapat.bankingledgersystem.account.domain.valueobject;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MoneyTest {

    @ParameterizedTest
    @ValueSource(doubles = { 0, 0.55, 1, 999, 999.999 })
    void validateMoneyAmount_whenItIsPositiveOrZero_pass(double value) {
        new Money(new BigDecimal(value));
    }

    @ParameterizedTest
    @ValueSource(doubles = { -1, -1.11, -999, -999.999 })
    void validateFormatAccountNumber_whenItIsNegative_throwException(double value) {
        IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Money(new BigDecimal(value));
        });

        Assertions.assertEquals(Money.INVALID_AMOUNT, ex.getMessage());
    }
}
