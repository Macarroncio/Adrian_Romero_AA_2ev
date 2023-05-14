package dao;

import domain.Customer;
import exception.CustomerAlreadyExistsException;
import exception.CustomerNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class CustomerDao {

    private Connection connection;

    public CustomerDao(Connection connection) {
        this.connection = connection;
    }


    public void add(Customer customer) throws SQLException, CustomerAlreadyExistsException {
        String sql = "INSERT INTO CUSTOMERS (id_customer, dni, c_password, c_name, c_surname, wallet) VALUES (?, ?, ?, ?, ?, ?)";
        if (existCustomer(customer.getDni()))
            throw new CustomerAlreadyExistsException("This customer already exists");
        //todo hacer autoincrementales
        //todo repasar la mecanica de los autoincrementales (que no le falte a ningun método)
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, autoincremental());
        statement.setString(2, customer.getDni());
        statement.setString(3, customer.getPassword());
        statement.setString(4, customer.getName());
        statement.setString(5, customer.getSurname());
        statement.setDouble(6, customer.getWallet());
        statement.executeUpdate();
    }

    public boolean delete(String dni) throws SQLException {
        try {
            String sql = "DELETE FROM customers WHERE dni = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, dni);
            int rows = statement.executeUpdate();

            return rows == 1;
        }catch (SQLException sqle){
            System.out.println("Problems probably related with this customer appearing in other tables");
            sqle.printStackTrace();
            return false;
        }
    }

    public boolean modify(String dni, Customer customer) throws SQLException {
        String sql = "UPDATE customers SET c_password = ?, c_name = ?, c_surname = ?, wallet = ? WHERE dni = ?";
        //todo hacer lo mismo que en los travels
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, autoincremental());
        statement.setString(2, customer.getDni());
        statement.setString(3, customer.getPassword());
        statement.setString(4, customer.getName());
        statement.setString(5, customer.getSurname());
        statement.setDouble(6, customer.getWallet());
        int rows = statement.executeUpdate();
        return rows == 1;
    }

    public ArrayList<Customer> showAll() throws SQLException {
        String sql = "SELECT * FROM CUSTOMERS ORDER BY c_surname";
        ArrayList<Customer> customers = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Customer customer = new Customer();
            customer.setId(resultSet.getInt("id_customer"));
            customer.setDni(resultSet.getString("dni"));
            customer.setPassword("*********");
            customer.setName(resultSet.getString("c_name"));
            customer.setSurname(resultSet.getString("c_surname"));
            customer.setWallet(resultSet.getDouble("wallet"));
            customers.add(customer);
        }

        return customers;
    }

    public Optional<Customer> findByDni(String dni) throws SQLException {
        String sql = "SELECT * FROM customers WHERE dni = ?";
        Customer customer = null;

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, dni);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            customer = new Customer();
            customer.setId(resultSet.getInt("id_customer"));
            customer.setDni(resultSet.getString("dni"));
            customer.setPassword("*********");
            customer.setName(resultSet.getString("c_name"));
            customer.setSurname(resultSet.getString("c_surname"));
            customer.setWallet(resultSet.getDouble("wallet"));

        }

        return Optional.ofNullable(customer);
    }

    private boolean existCustomer(String dni) throws SQLException {
        Optional<Customer> customer = findByDni(dni);
        return customer.isPresent();
    }
    private int autoincremental() throws SQLException {
        String sql = "SELECT COUNT (*) as autocount FROM CUSTOMERS";
        int autoincremental;

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        autoincremental = resultSet.getInt("autocount");
        return autoincremental++;
    }
    public  Optional<Customer> login(String username, String password) throws SQLException {
        String sql = "SELECT * FROM customers WHERE dni = ? AND c_password = ?";
        Customer user = null;

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            user = fromResultSet(resultSet);
        }

        return Optional.ofNullable(user);
    }
    public Customer fromResultSet(ResultSet resultSet) throws SQLException {
        Customer user = new Customer();
        user.setId(resultSet.getInt("id_customer"));
        user.setName(resultSet.getString("c_name"));
        user.setDni(resultSet.getString("dni"));

        return user;
    }

}






