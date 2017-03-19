package dev.paytrack.paytrack.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dev.paytrack.paytrack.domain.Transaction;
import dev.paytrack.paytrack.foursquare.FoursquareAPI;
import dev.paytrack.paytrack.foursquare.FoursquareVenue;
import dev.paytrack.paytrack.utils.DateUtils;

/**
 * Created by albert on 19/03/17.
 *
 * @author albert
 */
public class TransactionServiceMock implements TransactionService {

    private final String[] llocs= {"Casa calvet Barcelona", "El tap Barcelona", "La tramoia Barcelona", "icebarcelona",
            "Cafe Royal Edimbourgh", "Frankenstain Edimbourgh","The dome Edimbourgh","The honours edinbourgh",
            "Convivio Zurich", "La pasta zurich","Loft five Zurich","Kronenhalle zurich",
            "Gusto Leo Firenze","La giostra Firenze","Terra Terra Firenze","La ghiotta firenze"
            };
    private final Double[] costos ={25.4,12.75,16.75,15.65,
        25.75,34.50,30.25,40.0,
        40.50,35.25,50.0,42.60,
        20.50,16.20,19.50,21.60};

    private final String[] dates = {"2016-12-20","2016-12-21","2016-12-22","2016-12-23",
            "2016-10-15","2016-10-16","2016-10-17","2016-10-18",
            "2017-01-10","2017-01-11","2017-01-12","2017-01-13",
            "2017-03-01","2017-03-02","2017-03-03","2017-03-04"};

    public List<Transaction> transaction;

    public TransactionServiceMock(){
        transaction = new ArrayList<>();
        for(int i = 0; i < 16; ++i){
            Transaction t = Transaction.builder()
                    .amount(costos[i])
                    .bookingDate(dates[i])
                    .counterPartyName(llocs[i])
                    .build();

            transaction.add(t);
        }
    }


    @Override
    public List<Transaction> getTransactionsByOriginIban(String originIban) {
        return transaction;
    }

    @Override
    public List<Transaction> getTransactionsByOriginIbanBetweenDates(String originIban, Date startDate, Date endDate) {
        List<Transaction> resultat = new ArrayList<>();

        for(Transaction t : transaction){

            Date date = DateUtils.parseStringToDate(t.getBookingDate());

            if(date.after(startDate) && date.before(endDate)){
                resultat.add(t);
            }

        }

        return resultat;
    }

    @Override
    public List<FoursquareVenue> getCurrentRecommendVenue() {
        FoursquareAPI foursquareAPI = ServiceFactory.getFoursquareAPI();
        return foursquareAPI.getCurrentVenues();
    }
}
