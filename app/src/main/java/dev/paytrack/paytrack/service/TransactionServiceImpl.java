package dev.paytrack.paytrack.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dev.paytrack.paytrack.domain.Transaction;
import dev.paytrack.paytrack.foursquare.FoursquareAPI;
import dev.paytrack.paytrack.foursquare.FoursquareVenue;
import dev.paytrack.paytrack.utils.DateUtils;

/**
 * Created by albert on 18/03/17.
 * @author albert
 */
public class TransactionServiceImpl implements TransactionService {


    @Override
    public List<Transaction> getTransactionsByOriginIban(String originIban) {
        List<Transaction> transactions = new ArrayList<>();

        // TODO Call Api using originIban

        return transactions;
    }

    @Override
    public List<Transaction> getTransactionsByOriginIbanBetweenDates(String originIban, Date startDate, Date endDate) {
        List<Transaction> transactions = new ArrayList<>();

        // TODO Call Api using originIban

        List<Transaction> resultApi = new ArrayList<>();
        for (Transaction transaction : resultApi) {
            Date bookingDate = DateUtils.parseStringToDate(transaction.getBookingDate());
            if (DateUtils.betweenTwoDates(bookingDate, startDate, endDate)) {
                transactions.add(transaction);
            }
        }

        return transactions;
    }

    @Override
    public Number getAverageAmountPerTransactionInEstablishment(String establishmentIban) {

        Double sum = 0.0;

        // TODO Call Api using establishmentIban as counterPartyIban

        List<Transaction> resultApi = new ArrayList<>();
        for (Transaction transaction : resultApi) {
            sum += transaction.getAmount();
        }

        Double transactionsCount = (double) resultApi.size();
        return sum/transactionsCount;
    }

    @Override
    public List<FoursquareVenue> getCurrentRecommendVenue() {
        FoursquareAPI foursquareAPI = ServiceFactory.getFoursquareAPI();
        return foursquareAPI.getCurrentVenues();
    }

}