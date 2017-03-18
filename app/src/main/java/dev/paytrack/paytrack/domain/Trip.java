package dev.paytrack.paytrack.domain;

import android.graphics.Bitmap;

import java.util.Date;

import dev.paytrack.paytrack.utils.DateUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Builder;

@Getter
@Setter
@Builder
@ToString
public class Trip {

    private Bitmap image;
    private String destination;
    private Date initialDate;
    private Date finalDate;
    private String price;

    public Trip(Bitmap image, String destination, Date initialDate, Date finalDate, String price) {
        this.image = image;
        this.destination = destination;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.price = price;
    }

    public String getDates() {
        return  DateUtils.dateToString(this.initialDate) +
                " – " +
                DateUtils.dateToString(this.finalDate);
    }
}
