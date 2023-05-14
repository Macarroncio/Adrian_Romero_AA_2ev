package servlet;

import dao.CustomerDao;
import dao.Database;
import domain.Customer;
import exception.CustomerAlreadyExistsException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/RegisterCustomerServlet")
public class RegisterCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");       // input name="title" del formulario
        String surname = request.getParameter("surname");
        String dni = request.getParameter("dni");
        String password = request.getParameter("password");
        String wallet = request.getParameter("wallet");


        Database database = new Database();
        CustomerDao customerDao = new CustomerDao(database.getConnection());
        Customer customer = new Customer(name, surname, dni, password, Double.parseDouble(wallet));
        try{
            response.sendRedirect("login.jsp");
            if (customerDao.findByDni(dni).isPresent()){

                out.println("<div class='alert alert-danger' role='alert'>This customer's DNI is already registered</div>");
            }else{
                customerDao.add(customer);
                out.println("<div class='alert alert-success' role='alert'>The user has been successfully registered </div>");

            }
        }catch (SQLException sqle){
            sqle.printStackTrace();
        }catch (CustomerAlreadyExistsException caee){
            caee.printStackTrace();
        }
    }
}