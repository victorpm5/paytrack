package dev.paytrack.paytrack.service;

import java.util.Date;
import java.util.List;

import dev.paytrack.paytrack.domain.Transaction;
import dev.paytrack.paytrack.foursquare.FoursquareVenue;

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
     * Get all transactions by customer's iban between two initialDate
     * @param originIban customer's iban
     * @param startDate start date
     * @param endDate end date
     * @return customer's transactions between two initialDate
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
     * @return recommend establishment list
     */
    List<FoursquareVenue> getCurrentRecommendVenue();

}
