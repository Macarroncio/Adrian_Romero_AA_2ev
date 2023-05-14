package servlet;

import dao.Database;
import dao.TravelDao;
import domain.Customer;
import domain.Travel;
import exception.TravelAlreadyExistsException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/ModifyTravel")
public class ModifyTravel extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Customer currentUser = (Customer) request.getSession().getAttribute("currentUser");
        if (currentUser == null || !currentUser.getDni().equals("111111111")) {
            response.sendRedirect("login.jsp");
        }

        String destination = request.getParameter("destination");
        String newDestination = request.getParameter("newdestination");
        String newPrice = request.getParameter("newprice");

        Travel travel = new Travel(newDestination, Double.parseDouble(newPrice));

        Database database = new Database();
        TravelDao travelDao = new TravelDao(database.getConnection());
        try {
             travelDao.modify(destination, travel);
             out.println("<div class='alert alert-success' role='alert'>Successfully modified</div>");

        } catch (TravelAlreadyExistsException baee) {
            out.println("<div class='alert alert-danger' role='alert'>Travel already exists!!!</div>");
            baee.printStackTrace();
        } catch (SQLException sqle) {
            out.println("<div class='alert alert-danger' role='alert'>Problems encountered while adding this travel</div>");
            sqle.printStackTrace();
        }
    }
}