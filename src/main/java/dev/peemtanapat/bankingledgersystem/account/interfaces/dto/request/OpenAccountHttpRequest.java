package dev.peemtanapat.bankingledgersystem.account.interfaces.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OpenAccountHttpRequest(
        @NotNull(message = "accountName must not be null or blank") @NotBlank(message = "accountName must not be null or blank") String accountName,
        @Min(value = 0, message = "initialBalance must not be negative") BigDecimal initialBalance) {
}
