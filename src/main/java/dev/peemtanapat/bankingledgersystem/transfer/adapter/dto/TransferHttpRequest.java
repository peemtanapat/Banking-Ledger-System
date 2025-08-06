package dev.peemtanapat.bankingledgersystem.transfer.adapter.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record TransferHttpRequest(
        @NotNull(message = "fromAccountId must not be null") Long fromAccountId,
        @NotNull(message = "toAccountId must not be null") Long toAccountId,
        @NotNull(message = "toAccountId must not be null") @Min(value = 0, message = "initialBalance must not be negative") BigDecimal amount) {

}
