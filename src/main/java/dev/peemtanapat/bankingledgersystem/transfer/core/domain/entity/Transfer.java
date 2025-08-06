package dev.peemtanapat.bankingledgersystem.transfer.core.domain.entity;

import dev.peemtanapat.bankingledgersystem.account.domain.entity.Account;
import dev.peemtanapat.bankingledgersystem.transfer.core.domain.enums.DestinationType;
import dev.peemtanapat.bankingledgersystem.transfer.core.domain.enums.TransactionChannel;
import dev.peemtanapat.bankingledgersystem.transfer.core.domain.enums.TransactionStatus;
import dev.peemtanapat.bankingledgersystem.transfer.core.domain.enums.TransactionType;
import dev.peemtanapat.bankingledgersystem.transfer.core.domain.valueobject.Money;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Transfer {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "from_account_id", nullable = false)
    private Account fromAccountId;

    @ManyToOne
    @JoinColumn(name = "to_account_id")
    private Account toAccountId;

    @Embedded
    private Money amount;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private TransactionChannel transactionChannel;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private TransactionStatus transactionStatus = TransactionStatus.SUCCESS;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private DestinationType destinationType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(Account fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public Account getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(Account toAccountId) {
        this.toAccountId = toAccountId;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    public TransactionChannel getTransactionChannel() {
        return transactionChannel;
    }

    public void setTransactionChannel(TransactionChannel transactionChannel) {
        this.transactionChannel = transactionChannel;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public DestinationType getDestinationType() {
        return destinationType;
    }

    public void setDestinationType(DestinationType destinationType) {
        this.destinationType = destinationType;
    }

}
