<%@ page import="dao.Database" %>
<%@ page import="dao.CustomerDao" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.List" %>
<%@ page import="domain.Customer" %>

<%
    Customer currentUser = (Customer) session.getAttribute("currentUser");
    if (currentUser == null || !currentUser.getDni().equals("111111111")) {
        response.sendRedirect("login.jsp");
    }
%>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
    <script type="text/javascript">
            $(document).ready(function() {
                $("form").on("submit", function(event) {
                    event.preventDefault();
                    var formValue = $(this).serialize();
                    $.post("DeleteCustomerServlet", formValue, function(data) {
                        $("#result").html(data);
                    });
                });
            });
        </script>
    <div class="container">
        <h2>Listado de clientes</h2>
        <ul class="list-group">
            <%
                Database database = new Database();
                CustomerDao customerDao = new CustomerDao(database.getConnection());
                try {
                    List<Customer> customers = customerDao.showAll();
                    for (Customer customer : customers) {
            %>
                        <li class="list-group-item">
                            <a target="_blank" href="customer.jsp?dni=<%= customer.getDni() %>"><%= customer.getName() %></a>
            <%
                    }
               } catch (SQLException sqle) {
            %>
                    <div class="alert alert-danger" role="alert">
                      Error conectando con la base de datos
                    </div>
            <%
               }
            %>
        </ul>
    </div>
</body>
</html>