package servlet;

import dao.CustomerDao;
import dao.Database;
import domain.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String dni = request.getParameter("dni");       // input name="title" del formulario
        String password = request.getParameter("password");


        Database database = new Database();
        CustomerDao customerDao = new CustomerDao(database.getConnection());
        try {
            Optional<Customer> user = customerDao.login(dni, password);
            if (user.isPresent()){
                Customer usableUser = user.orElse(null);
                HttpSession session = request.getSession(true);
                if (usableUser.getDni().equals("111111111")){
                    session.setAttribute("currentUser", usableUser);
                    System.out.println("sesión iniciada");
                    response.sendRedirect("adminZone.jsp");

                }else{
                    session.setAttribute("currentUser", user.get());
                    System.out.println("sesión iniciada");
                    response.sendRedirect("customerZone.jsp");
                }
            } else {
                response.sendRedirect("login.jsp");
            }
            System.out.println("aqui");
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}
