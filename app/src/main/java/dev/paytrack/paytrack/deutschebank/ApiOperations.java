package dev.paytrack.paytrack.deutschebank;

import java.util.List;

import dev.paytrack.paytrack.domain.Address;
import dev.paytrack.paytrack.domain.CashAccount;
import dev.paytrack.paytrack.domain.Transaction;
import dev.paytrack.paytrack.domain.UserInfo;

public interface ApiOperations {

    public List<Address> getAddress();

    public List<CashAccount> getCashAccount();

    public List<Transaction> getTransactions();

    public UserInfo getUserInfo();
}

