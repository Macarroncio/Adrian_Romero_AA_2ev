package servlet;

import dao.Database;
import dao.CustomerDao;
import domain.Customer;
import domain.Customer;
import exception.CustomerAlreadyExistsException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/modify-customer")
public class ModifyCustomer extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Customer currentUser = (Customer) request.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            response.sendRedirect("login.jsp");
        }
        //de esta forma evitamos que se puedan modificar usuarios que no sean con el que te has logeado
        String dni = currentUser.getDni();

        String newName = request.getParameter("newName");
        String newSurname = request.getParameter("newSurname");
        String newDni = request.getParameter("newDni");
        String newPassword = request.getParameter("newPassword");

        Customer customer = new Customer();

        Database database = new Database();
        CustomerDao customerDao = new CustomerDao(database.getConnection());
        try {
            customer.setDni(newDni);
            customer.setName(newName);
            customer.setSurname(newSurname);
            customer.setPassword(newPassword);

            if(customerDao.modify(dni, customer)){

                request.getSession().setAttribute(currentUser.getDni(), newDni);
                out.println("<div class='alert alert-success' role='alert'>ahy que cambiar</div>");
            } else {
                out.println("<div class='alert alert-danger' role='alert'>Problems encountered while adding this customer</div>");
                out.println(newDni + newName + newSurname + newPassword + dni);
            }
        } catch (SQLException sqle) {
            out.println("<div class='alert alert-danger' role='alert'>Problems encountered while adding this customer</div>");
            sqle.printStackTrace();
        }
    }
}