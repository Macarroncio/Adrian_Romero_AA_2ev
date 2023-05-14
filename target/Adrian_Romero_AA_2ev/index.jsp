<%@ page import="dao.Database" %>
<%@ page import="dao.TravelDao" %>
<%@ page import="domain.Customer" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.List" %>
<%@ page import="domain.Travel" %>
<%
    Customer currentUser = (Customer) session.getAttribute("currentUser");
    if (currentUser == null) {
        response.sendRedirect("login.jsp");
    }
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Viajes San Valero</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Viajes San Valero</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav mr-auto"></ul>
                <form class="d-flex" action="logout" method="POST">
                    <button class="btn btn-outline-primary" type="submit">Log Off</button>
                </form>
            </div>
        </div>
    </nav>
    <script type="text/javascript">
                $(document).ready(function() {
                    $("form").on("submit", function(event) {
                        event.preventDefault();
                        var formValue = $(this).serialize();
                        $.post("delete-travel", formValue, function(data) {
                            $("#result").html(data);
                        });
                    });
                });
        </script>
    <div class="container-fluid text-center">
        <div class="container">
            <div class="row justify-content-center mt-5">
                <div class="col-md-6">
                    <form class="d-flex">
                        <select class="form-select me-2" name="param1">
                            <option value="" selected>Precio</option>
                            <option value="param1_value1">0-50</option>
                            <option value="param1_value2">50-200</option>
                            <option value="param1_value3">+200</option>
                        </select>
                        <input type="text" class="form-control me-2" placeholder="Identificador" name="searchQuery">
                        <button class="btn btn-primary" type="submit">Search</button>
                    </form>
                </div>
            </div>
        </div>
        <h2>Listado de viajes</h2>
        <ul id="travelList" class="list-group mt-5 mx-auto" style="max-width: 400px;">
            <%
                Database database = new Database();
                TravelDao travelDao = new TravelDao(database.getConnection());
                try {
                    List<Travel> travels = travelDao.showAll();
                    for (Travel travel : travels) {
            %>
                        <li class="list-group-item text-center">
                            <a target="_blank" href="travel.jsp?destination=<%= travel.getDestination() %>"><%= travel.getDestination() %></a>
                            <%
                                 if (currentUser == null || currentUser.getDni().equals("111111111")) {
                             %>
                                 <form>
                                   <input type="hidden" name="travelDestination" value="<%= travel.getDestination() %>">
                                   <button type="submit" class="btn btn-primary">Borrar</button>
                                 </form>
                             <%
                                 }
                             %>
                         </li>
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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    </body>
    </html>

