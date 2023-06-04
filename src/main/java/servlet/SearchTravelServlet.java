package servlet;

import dao.Database;
import dao.TravelDao;
import domain.Travel;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet("/search-travel")
public class SearchTravelServlet extends HttpServlet {
        protected  void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            String destination = request.getParameter("searchQuery");
            Optional<Travel> optionalTravel;
            Travel travel;
            try {
                Database db = new Database();
                TravelDao travelDao = new TravelDao(db.getConnection());
                optionalTravel = travelDao.findByDestination(destination);
                travel = optionalTravel.orElse(null);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if (travel != null) {
                response.sendRedirect("travel.jsp?destination=" + destination);
            } else {
                response.sendRedirect("errores.jsp");
            }

        }
    }

