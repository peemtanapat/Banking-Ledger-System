package dev.peemtanapat.bankingledgersystem.transfer.core.application.port.in;

import dev.peemtanapat.bankingledgersystem.transfer.core.application.command.TransferCommand;
import dev.peemtanapat.bankingledgersystem.transfer.core.application.dto.TransferDto;

public interface TransferUseCase {

    TransferDto transfer(TransferCommand transferCommand);
}
