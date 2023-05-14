package domain;

public class Customer {
    private int id;
    private String name;
    private String surname;
    private String dni;
    private String password;

    private double wallet;

    public Customer(String name, String surname, String dni, String password, double wallet) {
        this.name = name;
        this.surname = surname;
        this.dni = dni;
        this.password = password;
        this.wallet = wallet;
    }

    public Customer() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }
    //todo retocar el tostring
    @Override
    public String toString() {
        return
                "ID=" + id +
                ", NAME='" + name + '\'' +
                ", SURNAME='" + surname + '\'' +
                ", DNI='" + dni + '\'' +
                ", PASSWORD='" + password + '\'' +
                ", WALLET=" + wallet
                ;
    }
}
