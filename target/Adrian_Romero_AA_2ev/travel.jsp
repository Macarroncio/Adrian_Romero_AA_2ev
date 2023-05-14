<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="dao.Database" %>
<%@ page import="dao.TravelDao" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="domain.Travel" %>
<%@ page import="java.util.Optional" %>
<%
Connection connection;
Database db = new Database();
connection = db.getConnection();
TravelDao travelDao = new TravelDao(connection);
String travelDestination = request.getParameter("destination");
Optional<Travel> optionalTravel = travelDao.findByDestination(travelDestination);
Travel travel = optionalTravel.orElse(null);
Double price = travel.getPrice();
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Detalles del viaje</title>
      <style>
             .card-body {
               white-space: nowrap;
             }
           </style>
     <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
</head>
<body>
  <div class="container-fluid d-flex align-items-center justify-content-center vh-100">
    <div class="card">
      <div class="card-body">
        <div class="row">
          <div class="col-md-6">
            <h5>Destination:</h5>
          </div>
          <div class="col-md-6">
            <h5><%= travelDestination %></h5>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <h5>Price: </h5>
          </div>
          <div class="col-md-6">
            <h5><%= price %></h5>
          </div>
        </div>
        <div class="row">
          <div class="col-md-12">
            <button class="btn btn-primary">Button</button>
          </div>
        </div>
      </div>
    </div>
  </div>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.2/js/bootstrap.bundle.min.js" integrity="sha512-PJ9zdEanfLiqSU7p3qKjgvG1dJcFNUcbRn+QK1e9a9fyV7yTfHn1sGnb1sBfkv4KZ4sj4ELf+MHWOWaXfGbGxg==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</body>
</html>

