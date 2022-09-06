package servlet;

import dao.CustomerDao;
import dao.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String dni = request.getParameter("dni");       // input name="title" del formulario
        String password = request.getParameter("password");

        Database database = new Database();
        CustomerDao bookDao = new CustomerDao(database.getConnection());
        try {
            if (bookDao.findByDni(dni) != null){
                out.println("<div class='alert alert-success' role='alert'>El libro se ha registrado correctamente</div>");
            } else{
                out.println("<div class='alert alert-danger' role='alert'>Se ha producido un error al registrar el libro</div>");
            }
        }  catch (SQLException sqle) {
            out.println("<div class='alert alert-danger' role='alert'>Se ha producido un error al registrar el libro</div>");
            sqle.printStackTrace();
        }

    }






        /* Database database = new Database();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String dni = request.getParameter("dni");
        session.setAttribute("dni", dni);
        String password = request.getParameter("password");

        try {
            Connection connection = database.getConnection();
            String sql = "SELECT dni, c_password FROM customers where dni = ? AND c_password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, dni);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                out.print("You are successfully loggedin...");
                request.getRequestDispatcher("Welcome").include(request, response);
            } else {
                out.println("Username or Password incorrect");
                request.getRequestDispatcher("Login.html").include(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
}
