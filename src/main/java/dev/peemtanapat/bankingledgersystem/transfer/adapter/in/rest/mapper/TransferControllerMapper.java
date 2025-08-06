package dev.peemtanapat.bankingledgersystem.transfer.adapter.in.rest.mapper;

import org.springframework.stereotype.Service;

import dev.peemtanapat.bankingledgersystem.transfer.adapter.dto.TransferHttpRequest;
import dev.peemtanapat.bankingledgersystem.transfer.adapter.dto.TransferHttpResponse;
import dev.peemtanapat.bankingledgersystem.transfer.core.application.command.TransferCommand;
import dev.peemtanapat.bankingledgersystem.transfer.core.application.dto.TransferDto;

@Service
public class TransferControllerMapper {

    public TransferCommand requestToCommand(TransferHttpRequest request) {
        return new TransferCommand(
                request.fromAccountId(),
                request.toAccountId(),
                request.amount());
    }

    public TransferHttpResponse dtoToResponse(TransferDto transferDto) {
        return new TransferHttpResponse(
                transferDto.id(),
                transferDto.fromAccountId(),
                transferDto.toAccountId(),
                transferDto.amount(),
                transferDto.transactionStatus(),
                transferDto.transactionChannel(),
                transferDto.destinationType(),
                transferDto.transactionType());
    }
}
