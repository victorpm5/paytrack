package dev.paytrack.paytrack.service;

import java.util.Date;
import java.util.List;

import dev.paytrack.paytrack.domain.Transaction;
import dev.paytrack.paytrack.foursquare.FoursquareAPI;
import dev.paytrack.paytrack.foursquare.FoursquareVenue;

/**
 * Created by albert on 19/03/17.
 *
 * @author albert
 */
public class TransactionServiceMock implements TransactionService {

    @Override
    public List<Transaction> getTransactionsByOriginIban(String originIban) {
        return null;
    }

    @Override
    public List<Transaction> getTransactionsByOriginIbanBetweenDates(String originIban, Date startDate, Date endDate) {
        return null;
    }

    @Override
    public List<FoursquareVenue> getCurrentRecommendVenue() {
        FoursquareAPI foursquareAPI = ServiceFactory.getFoursquareAPI();
        return foursquareAPI.getCurrentVenues();
    }
}
