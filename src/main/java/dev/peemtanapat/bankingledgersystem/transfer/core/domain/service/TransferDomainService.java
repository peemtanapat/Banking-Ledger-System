package dev.peemtanapat.bankingledgersystem.transfer.core.domain.service;

import org.springframework.stereotype.Service;

import dev.peemtanapat.bankingledgersystem.account.domain.entity.Account;
import dev.peemtanapat.bankingledgersystem.transfer.core.application.port.out.TransferRepository;
import dev.peemtanapat.bankingledgersystem.transfer.core.domain.entity.Transfer;
import dev.peemtanapat.bankingledgersystem.transfer.core.domain.enums.DestinationType;
import dev.peemtanapat.bankingledgersystem.transfer.core.domain.enums.TransactionChannel;
import dev.peemtanapat.bankingledgersystem.transfer.core.domain.valueobject.Money;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TransferDomainService {

    private final TransferRepository transferRepository;

    public Transfer transfer(long fromAccount, long toAccount, Money amount) {
        Transfer transfer = new Transfer();
        transfer.setFromAccountId(new Account(fromAccount));
        transfer.setToAccountId(new Account(toAccount));
        transfer.setAmount(amount);

        transfer.setDestinationType(DestinationType.SAME_CUSTOMER);
        transfer.setTransactionChannel(TransactionChannel.Payment);

        return transferRepository.save(transfer);
    }

}
