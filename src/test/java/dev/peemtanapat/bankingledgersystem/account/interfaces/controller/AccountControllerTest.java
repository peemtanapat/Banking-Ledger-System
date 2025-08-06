package dev.peemtanapat.bankingledgersystem.account.interfaces.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.peemtanapat.bankingledgersystem.account.application.dto.AccountDto;
import dev.peemtanapat.bankingledgersystem.account.domain.enums.AccountStatus;
import dev.peemtanapat.bankingledgersystem.account.domain.enums.AccountType;
import dev.peemtanapat.bankingledgersystem.account.domain.enums.Currency;
import dev.peemtanapat.bankingledgersystem.account.domain.repository.AccountRepository;
import dev.peemtanapat.bankingledgersystem.account.interfaces.dto.response.AccountHttpResponse;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private AccountRepository accountRepository;

        @Autowired
        private ObjectMapper objectMapper;

        private static final String OPEN_SAV_ACC_URI = "/api/v1/accounts/savings";

        @Test
        void openSavingsAccount_whenAllInputIsCorrect_should200() throws Exception {

                // arrange
                String accountName = "TANAPAT CHOOCHOT";
                BigDecimal initialBalance = new BigDecimal("500.55");
                String requestBody = """
                                {
                                      "accountName": "%s",
                                      "initialBalance": "%f"
                                  }
                                  """.formatted(
                                accountName,
                                initialBalance);

                // action
                MvcResult response = mockMvc.perform(
                                post(OPEN_SAV_ACC_URI)
                                                .content(requestBody)
                                                .contentType("application/json"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.accountName").value(accountName))
                                .andExpect(jsonPath("$.accountType").value(AccountType.SAVINGS.name()))
                                .andExpect(jsonPath("$.balance").value(initialBalance))
                                .andReturn();

                // assertion
                String responseStr = response.getResponse().getContentAsString();
                AccountHttpResponse newAccount = objectMapper.readValue(responseStr, AccountHttpResponse.class);

                accountRepository.findById(newAccount.accountId())
                                .ifPresentOrElse(acc -> {
                                        assert acc.getAccountName().getValue().equals(accountName);
                                        assert acc.getAccountNumber().getValue().length() == 10;
                                        assert acc.getAccountType().equals(AccountType.SAVINGS);
                                        assert acc.getAccountStatus().equals(AccountStatus.ACTIVE);
                                        assert acc.getBalance().getAmount().equals(initialBalance);
                                        assert acc.getBalance().getCurrency().equals(Currency.THB);
                                }, () -> {
                                        throw new NoSuchElementException(
                                                        "Not found any account id=" + newAccount.accountId());
                                });
        }

        @Test
        void openSavingsAccount_whenAccountNameIsBlank_should400() throws Exception {
                // arrange
                String accountName = "";
                BigDecimal initialBalance = new BigDecimal("500.55");
                String requestBody = """
                                {
                                      "accountName": "%s",
                                      "initialBalance": "%f"
                                  }
                                  """.formatted(
                                accountName,
                                initialBalance);

                // action & assertion
                mockMvc.perform(
                                post(OPEN_SAV_ACC_URI)
                                                .content(requestBody)
                                                .contentType("application/json"))
                                .andExpect(status().isBadRequest())
                                .andExpect(jsonPath("$.accountName").value("accountName must not be null or blank"))
                                .andReturn();
        }

        @Test
        void openSavingsAccount_whenInitialBalanceIsNegative_should400() throws Exception {
                // arrange
                String accountName = "TANAPAT CHOOCHOT";
                BigDecimal initialBalance = new BigDecimal("-5");
                String requestBody = """
                                {
                                      "accountName": "%s",
                                      "initialBalance": "%f"
                                  }
                                  """.formatted(
                                accountName,
                                initialBalance);

                // action & assertion
                mockMvc.perform(
                                post(OPEN_SAV_ACC_URI)
                                                .content(requestBody)
                                                .contentType("application/json"))
                                .andExpect(status().isBadRequest())
                                .andExpect(jsonPath("$.initialBalance").value("initialBalance must not be negative"))
                                .andReturn();
        }
}
