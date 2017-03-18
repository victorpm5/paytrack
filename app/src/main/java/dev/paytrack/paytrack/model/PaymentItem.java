package dev.paytrack.paytrack.model;

public class PaymentItem {

    private String name;
    private Double price;

    public PaymentItem(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }
}
