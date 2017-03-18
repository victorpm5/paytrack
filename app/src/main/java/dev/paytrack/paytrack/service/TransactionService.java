package dev.paytrack.paytrack.service;

import java.util.Date;
import java.util.List;

import dev.paytrack.paytrack.domain.Establishment;
import dev.paytrack.paytrack.domain.Transaction;

/**
 * Created by albert on 18/03/17.
 * @author albert
 */
public interface TransactionService {

    /**
     * Get all transactions by customer's iban
     * @param originIban customer's iban
     * @return all customer's transactions
     */
    List<Transaction> getTransactionsByOriginIban(String originIban);

    /**
     * Get all transactions by customer's iban between two dates
     * @param originIban customer's iban
     * @param startDate start date
     * @param endDate end date
     * @return customer's transactions between two dates
     */
    List<Transaction> getTransactionsByOriginIbanBetweenDates(String originIban, Date startDate, Date endDate);

    /**
     * Get average amount in an establishment
     * @param establishmentIban establishment iban
     * @return average amount
     */
    Number getAverageAmountPerTransactionInEstablishment(String establishmentIban);

    /**
     * Get recommend establishments
     * @param remainBudget remain budget
     * @param endDate last travel day
     * @param city travel city
     * @return recommend establishment list
     */
    List<Establishment> getRecommendEstablishments(Number remainBudget, Date endDate, String city);

}
