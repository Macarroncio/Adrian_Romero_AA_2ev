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
        Customer customer = new Customer("name", "surname", "otrodni", "password", 666.66);

        TravelDao travelDao = new TravelDao(connection);
        Travel travel = new Travel("laprueba", 10.20);


        try {
            travelDao.modify("Acapulco Shore", travel);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (TravelAlreadyExistsException caee) {
            caee.printStackTrace();
        }

    }
}
