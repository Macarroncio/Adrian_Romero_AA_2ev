package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntFunction;

public class Travel {
    private int id;
    private String destination;
    private double price;
    private List<Purchase> purchases;

    public Travel(String destination, double price) {
        this.destination = destination;
        this.price = price;
        this.purchases = new ArrayList<>();
    }

    public Travel() {
        this.purchases = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }
//todo rehacer tostring
    @Override
    public String toString() {
        return "Travel{" +
                "id=" + id +
                ", destination='" + destination + '\'' +
                ", price=" + price +
                ", purchases=" + purchases +
                '}';
    }
}
