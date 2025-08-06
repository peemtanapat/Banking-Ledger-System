package dev.peemtanapat.bankingledgersystem.transfer.adapter.dto;

import java.math.BigDecimal;

import dev.peemtanapat.bankingledgersystem.transfer.core.domain.enums.DestinationType;
import dev.peemtanapat.bankingledgersystem.transfer.core.domain.enums.TransactionChannel;
import dev.peemtanapat.bankingledgersystem.transfer.core.domain.enums.TransactionStatus;
import dev.peemtanapat.bankingledgersystem.transfer.core.domain.enums.TransactionType;

public record TransferHttpResponse(
        Long id,
        Long fromAccountId,
        Long toAccountId,
        BigDecimal amount,
        TransactionStatus transactionStatus,
        TransactionChannel transactionChannel,
        DestinationType destinationType,
        TransactionType transactionType) {

}
