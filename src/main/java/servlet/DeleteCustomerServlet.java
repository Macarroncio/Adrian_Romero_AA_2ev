package servlet;

import dao.CustomerDao;
import dao.Database;
import domain.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/delete-customer")
public class DeleteCustomerServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Customer currentUser = (Customer) request.getSession().getAttribute("currentUser");
        String dniCustomer = request.getParameter("customerDni");
        if (currentUser == null) {
            response.sendRedirect("login.jsp");
        }
        try{

            Database db = new Database();
            Connection connection = db.getConnection();
            CustomerDao customerDao = new CustomerDao(connection);
            customerDao.delete(dniCustomer);

            response.sendRedirect("customerList.jsp");
        }catch (SQLException sqle){
            out.println("<div class='alert alert-danger' role='alert'>Problems encountered while adding this travel</div>");
            sqle.printStackTrace();
        }
    }
}