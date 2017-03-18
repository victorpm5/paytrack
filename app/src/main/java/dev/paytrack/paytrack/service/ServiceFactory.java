package dev.paytrack.paytrack.service;

/**
 * Created by albert on 18/03/17.
 *
 * @author albert
 */
public class ServiceFactory {

    private static TransactionServiceImpl transactionService;

    public static TransactionServiceImpl getRestaurantService(){
        if (transactionService == null)
            transactionService = new TransactionServiceImpl();
        return transactionService;
    }

}
