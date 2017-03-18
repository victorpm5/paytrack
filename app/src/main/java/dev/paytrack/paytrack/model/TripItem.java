package dev.paytrack.paytrack.model;

import android.graphics.Bitmap;

public class TripItem {

    private Bitmap image;
    private String destination;
    private String dates;
    private String price;

    public TripItem(Bitmap image, String destination, String dates, String price) {
        this.image = image;
        this.destination = destination;
        this.dates = dates;
        this.price = price;
    }

    public Bitmap getImage() {
        return image;
    }

    public String getDestination() {
        return destination;
    }

    public String getDates() {
        return dates;
    }

    public String getPrice() {
        return price;
    }
}
