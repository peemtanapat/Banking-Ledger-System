package dev.peemtanapat.bankingledgersystem.transfer.core.application.command;

import java.math.BigDecimal;

public record TransferCommand(
        Long fromAccountId,
        Long toAccountId,
        BigDecimal amount) {

}
