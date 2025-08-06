package dev.peemtanapat.bankingledgersystem.transfer.core.application.dto;

import java.math.BigDecimal;

import dev.peemtanapat.bankingledgersystem.transfer.core.domain.enums.DestinationType;
import dev.peemtanapat.bankingledgersystem.transfer.core.domain.enums.TransactionChannel;
import dev.peemtanapat.bankingledgersystem.transfer.core.domain.enums.TransactionStatus;
import dev.peemtanapat.bankingledgersystem.transfer.core.domain.enums.TransactionType;

public record TransferDto(
                Long id,
                Long fromAccountId,
                Long toAccountId,
                BigDecimal amount,
                TransactionStatus transactionStatus,
                TransactionChannel transactionChannel,
                DestinationType destinationType,
                TransactionType transactionType) {

}
