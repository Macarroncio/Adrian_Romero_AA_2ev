package domain;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Purchase {
    private int id;
    private char paid;
    private LocalDate purchaseDate;
    private Customer customer;
    private List<Travel> travels;

    public Purchase(char paid, LocalDate purchaseDate, Customer customer, List<Travel> travels) {
        this.paid = paid;
        this.purchaseDate = purchaseDate;
        this.customer = customer;
        this.travels = travels;
    }
    public Purchase() {
        travels = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public char getPaid() {
        return paid;
    }

    public void setPaid(char paid) {
        this.paid = paid;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Travel> getTravels() {
        return travels;
    }

    public void setTravels(List<Travel> travels) {
        this.travels = travels;
    }
}


