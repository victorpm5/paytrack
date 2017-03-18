package dev.paytrack.paytrack.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionResponse {

    private List<Transaction> transactionList;

    public List<Transaction> getTransactionList() {
        return transactionList;
    }
}
