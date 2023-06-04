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
    <div class="container-fluid text-center">
        <div class="container">
            <div class="row justify-content-center mt-5">
                <div class="col-md-6">
                    <form class="d-flex" action="search-travel" method="POST">
                        <input type="text" class="form-control me-2" placeholder="Destino" name="searchQuery"><button class="btn btn-primary">Search</button>
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


                               <button type="submit" class="btn btn-outline-danger">
                                                       <a href="delete-travel?travelDestination=<%= travel.getDestination() %>">
                                                       <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                                           class="bi bi-x-octagon-fill" viewBox="0 0 16 16">
                                                           <path
                                                               d="M11.46.146A.5.5 0 0 0 11.107 0H4.893a.5.5 0 0 0-.353.146L.146 4.54A.5.5 0 0 0 0 4.893v6.214a.5.5 0 0 0 .146.353l4.394 4.394a.5.5 0 0 0 .353.146h6.214a.5.5 0 0 0 .353-.146l4.394-4.394a.5.5 0 0 0 .146-.353V4.893a.5.5 0 0 0-.146-.353L11.46.146zm-6.106 4.5L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 1 1 .708-.708z">
                                                           </path>
                                                       </svg>
                                                       </a>
                                                   </button>

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

