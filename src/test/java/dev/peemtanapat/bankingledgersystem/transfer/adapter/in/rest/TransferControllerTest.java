package dev.peemtanapat.bankingledgersystem.transfer.adapter.in.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.peemtanapat.bankingledgersystem.account.domain.entity.Account;
import dev.peemtanapat.bankingledgersystem.account.domain.enums.AccountType;
import dev.peemtanapat.bankingledgersystem.account.domain.repository.AccountRepository;
import dev.peemtanapat.bankingledgersystem.account.domain.valueobject.AccountName;
import dev.peemtanapat.bankingledgersystem.account.domain.valueobject.AccountNumber;
import dev.peemtanapat.bankingledgersystem.account.domain.valueobject.Money;
import dev.peemtanapat.bankingledgersystem.transfer.adapter.dto.TransferHttpResponse;
import dev.peemtanapat.bankingledgersystem.transfer.core.application.port.out.TransferRepository;
import dev.peemtanapat.bankingledgersystem.transfer.core.domain.entity.Transfer;

@SpringBootTest
@AutoConfigureMockMvc
public class TransferControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransferRepository transferRepository;

    @BeforeEach
    void initial() {
        Account firstAccount = new Account();
        firstAccount.setAccountName(new AccountName("A"));
        firstAccount.setAccountNumber(new AccountNumber("1111111111"));
        firstAccount.setBalance(new Money("1000.00"));
        firstAccount.setAccountType(AccountType.SAVINGS);
        Account secondAccount = new Account();
        secondAccount.setAccountName(new AccountName("B"));
        secondAccount.setAccountNumber(new AccountNumber("2222222222"));
        secondAccount.setBalance(new Money("800.00"));
        secondAccount.setAccountType(AccountType.SAVINGS);

        accountRepository.save(firstAccount);
        accountRepository.save(secondAccount);
    }

    @AfterEach
    void clean() {
        transferRepository.deleteAll();
        accountRepository.deleteAll();
    }

    @Test
    void transfer_whenAllDataIsCorrect_should200() throws Exception {
        // arrange
        long fromAccountId = 1;
        long toAccountId = 2;
        BigDecimal amount = new BigDecimal("500.55");
        String requestBody = """
                {
                      "fromAccountId": "%d",
                      "toAccountId": "%d",
                      "amount": "%f"
                  }
                  """.formatted(
                fromAccountId,
                toAccountId,
                amount);

        // action
        MvcResult mvcResult = mockMvc.perform(post("/api/v1/transfer")
                .content(requestBody)
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();

        // assert
        String responseStr = mvcResult.getResponse().getContentAsString();
        TransferHttpResponse response = objectMapper.readValue(responseStr, TransferHttpResponse.class);

        Assertions.assertEquals(fromAccountId, response.fromAccountId());
        Assertions.assertEquals(toAccountId, response.toAccountId());
        Assertions.assertTrue(amount.compareTo(response.amount()) == 0);

        transferRepository.findById(response.id()).ifPresentOrElse(trx -> {
            Assertions.assertEquals(fromAccountId, trx.getFromAccountId().getId());
            Assertions.assertEquals(toAccountId, trx.getToAccountId().getId());
            Assertions.assertEquals(amount, trx.getAmount().getAmount());
        }, () -> {
            throw new NoSuchElementException("Not found transaction id=" + response.id());
        });

        accountRepository.findById(fromAccountId).ifPresentOrElse(acc -> {
            Assertions.assertEquals(new BigDecimal("1000.00").subtract(amount), acc.getBalance());
        }, () -> {
            throw new NoSuchElementException("Not found account id=" + fromAccountId);
        });

        accountRepository.findById(toAccountId).ifPresentOrElse(acc -> {
            Assertions.assertEquals(new BigDecimal("800.00").add(amount), acc.getBalance());
        }, () -> {
            throw new NoSuchElementException("Not found account id=" + toAccountId);
        });
    }

}
