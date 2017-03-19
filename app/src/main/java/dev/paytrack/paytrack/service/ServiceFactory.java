package dev.paytrack.paytrack.service;

import dev.paytrack.paytrack.foursquare.FoursquareAPIImpl;

/**
 * Created by albert on 18/03/17.
 *
 * @author albert
 */
public class ServiceFactory {

    private static TransactionServiceImpl transactionService;
    private static FoursquareAPIImpl foursquareAPI;

    public static TransactionServiceImpl getTransactionService(){
        if (transactionService == null)
            transactionService = new TransactionServiceImpl();
        return transactionService;
    }

    public static FoursquareAPIImpl getFoursquareAPI() {
        if (foursquareAPI == null) {
            foursquareAPI = new FoursquareAPIImpl();
        }
        return foursquareAPI;
    }

}
