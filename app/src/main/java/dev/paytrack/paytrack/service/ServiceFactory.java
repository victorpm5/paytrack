package dev.paytrack.paytrack.service;

import dev.paytrack.paytrack.foursquare.FoursquareAPIImpl;

/**
 * Created by albert on 18/03/17.
 *
 * @author albert
 */
public class ServiceFactory {

    private static TransactionServiceMock transactionService;
    private static FoursquareAPIImpl foursquareAPI;

    public static TransactionServiceMock getTransactionService(){
        if (transactionService == null)
            transactionService = new TransactionServiceMock();
        return transactionService;
    }

    public static FoursquareAPIImpl getFoursquareAPI() {
        if (foursquareAPI == null) {
            foursquareAPI = new FoursquareAPIImpl();
        }
        return foursquareAPI;
    }

}
