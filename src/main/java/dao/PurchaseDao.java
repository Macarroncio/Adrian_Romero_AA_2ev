package dao;

import domain.Customer;
import domain.Travel;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PurchaseDao {
    private Connection connection;

    public PurchaseDao(Connection connection) {
        this.connection = connection;
    }

    public void add(Customer customer, List<Travel> travels) throws SQLException {
        String purchaseSql = "INSERT INTO purchases (id_purchase, paid, p_date, id_customer) VALUES (?, ?, ?, ?)";
        //todo crear un metodo paid
        //todo comprobar que mete el char correctamente
        //todo meter close en todos lados
        connection.setAutoCommit(false);
        int idpurchase = autoincremental();

        PreparedStatement purchaseStatement = connection.prepareStatement(purchaseSql);
        purchaseStatement.setInt(1, idpurchase);
        purchaseStatement.setString(1, "0");
        purchaseStatement.setDate(3, new Date(System.currentTimeMillis()));
        purchaseStatement.setInt(2, customer.getId());
        purchaseStatement.executeUpdate();

        purchaseStatement.close();

        for (Travel travel : travels) {
            String travelSql = "INSERT INTO purchase_travel (id_purchase, id_travel, code) VALUES (?, ?, ?)";

            PreparedStatement travelStatement = connection.prepareStatement(travelSql);
            travelStatement.setInt(1, idpurchase);
            travelStatement.setInt(2, travel.getId());
            travelStatement.setString(3, UUID.randomUUID().toString());
            travelStatement.executeUpdate();
            travelStatement.close();
        }

        connection.commit();
        connection.setAutoCommit(true);

    }

    public void pay(int id_purchase) throws SQLException{
        String sql = "UPDATE PURCHASES paid = ? where id_purchase = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "1");
        statement.setInt(2, id_purchase);
    }

    private int autoincremental() throws SQLException {
        String sql = "SELECT COUNT (*) as autocount FROM purchases";
        int autoincremental;

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        autoincremental = resultSet.getInt("autocount");
        return autoincremental++;
    }
}






