package dev.peemtanapat.bankingledgersystem.transfer.adapter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.peemtanapat.bankingledgersystem.account.domain.repository.AccountRepository;
import dev.peemtanapat.bankingledgersystem.transfer.core.application.port.in.TransferUseCase;
import dev.peemtanapat.bankingledgersystem.transfer.core.application.service.TransferApplicationService;
import dev.peemtanapat.bankingledgersystem.transfer.core.domain.service.TransferDomainService;

@Configuration
public class TransferContextConfig {

    @Bean
    public TransferUseCase transferUseCase(
            TransferDomainService transferDomainService,
            AccountRepository accountRepository) {

        return new TransferApplicationService(
                transferDomainService,
                accountRepository);
    }
}
