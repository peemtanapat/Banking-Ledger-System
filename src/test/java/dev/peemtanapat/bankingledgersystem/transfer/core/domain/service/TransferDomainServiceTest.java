package dev.peemtanapat.bankingledgersystem.transfer.core.domain.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.peemtanapat.bankingledgersystem.account.domain.entity.Account;
import dev.peemtanapat.bankingledgersystem.transfer.core.application.port.out.TransferRepository;
import dev.peemtanapat.bankingledgersystem.transfer.core.domain.entity.Transfer;
import dev.peemtanapat.bankingledgersystem.transfer.core.domain.valueobject.Money;

@ExtendWith(MockitoExtension.class)
class TransferDomainServiceTest {

    @InjectMocks
    private TransferDomainService transferDomainService;

    @Mock
    private TransferRepository transferRepository;

    @Test
    void transfer_whenAllDataIsCorrect_shouldSuccess() {
        // arrange
        long fromAccount = 1;
        long toAccount = 2;
        Money amount = new Money("500.55");

        Transfer mockTransfer = new Transfer();
        mockTransfer.setAmount(amount);
        mockTransfer.setFromAccountId(new Account(fromAccount));
        mockTransfer.setToAccountId(new Account(toAccount));

        Mockito.when(transferRepository.save(Mockito.any())).thenReturn(mockTransfer);

        // assert
        Transfer transfer = transferDomainService.transfer(fromAccount, toAccount, amount);

        // assert
        Assertions.assertEquals(amount, transfer.getAmount());
    }

}
