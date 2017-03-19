package dev.paytrack.paytrack.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CashAccountResponse {

    private List<CashAccount> cashAccountList;

    public List<CashAccount> getCashAccountList() {
        return cashAccountList;
    }
}
