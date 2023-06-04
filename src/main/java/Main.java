import dao.CustomerDao;
import dao.Database;
import dao.TravelDao;
import domain.Customer;
import domain.Travel;
import exception.CustomerAlreadyExistsException;
import exception.TravelAlreadyExistsException;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class Main{
    public static void main (String args[]) {
        Connection connection;
        Database db = new Database();
        connection = db.getConnection();
        CustomerDao customerDao = new CustomerDao(connection);
        String dni = "73016529Q";
        Customer customer = new Customer();

        customer.setDni("7777");
        customer.setName("newName");
        customer.setSurname("newSurname");
        customer.setPassword("1234");
        try {
            customerDao.modify(dni, customer);
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
}
