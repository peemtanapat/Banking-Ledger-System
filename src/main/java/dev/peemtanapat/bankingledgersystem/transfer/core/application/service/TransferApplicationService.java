package dev.peemtanapat.bankingledgersystem.transfer.core.application.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.peemtanapat.bankingledgersystem.account.domain.entity.Account;
import dev.peemtanapat.bankingledgersystem.account.domain.repository.AccountRepository;
import dev.peemtanapat.bankingledgersystem.transfer.core.application.command.TransferCommand;
import dev.peemtanapat.bankingledgersystem.transfer.core.application.dto.TransferDto;
import dev.peemtanapat.bankingledgersystem.transfer.core.application.port.in.TransferUseCase;
import dev.peemtanapat.bankingledgersystem.transfer.core.domain.entity.Transfer;
import dev.peemtanapat.bankingledgersystem.transfer.core.domain.service.TransferDomainService;
import dev.peemtanapat.bankingledgersystem.transfer.core.domain.valueobject.Money;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class TransferApplicationService implements TransferUseCase {

    private final TransferDomainService transferDomainService;
    private final AccountRepository accountRepository;

    @Override
    public TransferDto transfer(TransferCommand transferCommand) {
        Account fromAccount = accountRepository.findById(transferCommand.fromAccountId()).get();
        Account toAccount = accountRepository.findById(transferCommand.toAccountId()).get();

        Transfer transfer = transferDomainService.transfer(
                transferCommand.fromAccountId(),
                transferCommand.toAccountId(),
                new Money(transferCommand.amount()));

        BigDecimal fromAccountNewBalance = fromAccount.getBalance().subtract(transferCommand.amount());
        fromAccount.setBalance(fromAccountNewBalance);
        accountRepository.save(fromAccount);

        BigDecimal toAccountNewBalance = toAccount.getBalance().add(transferCommand.amount());
        toAccount.setBalance(toAccountNewBalance);
        accountRepository.save(toAccount);

        return new TransferDto(
                transfer.getId(),
                transfer.getFromAccountId().getId(),
                transfer.getToAccountId().getId(),
                transfer.getAmount().getAmount(),
                transfer.getTransactionStatus(),
                transfer.getTransactionChannel(),
                transfer.getDestinationType(),
                transfer.getTransactionType());
    }

}
