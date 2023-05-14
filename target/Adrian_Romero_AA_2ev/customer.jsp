<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="dao.Database" %>
<%@ page import="dao.CustomerDao" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="domain.Customer" %>
<%@ page import="java.util.Optional" %>
<%
Connection connection;
Database db = new Database();
connection = db.getConnection();
CustomerDao customerDao = new CustomerDao(connection);
String customerDni = request.getParameter("dni");
Optional<Customer> optionalCustomer = customerDao.findByDni(customerDni);
Customer customer = optionalCustomer.orElse(null);
int id = customer.getId();
String name = customer.getName();
String surname = customer.getSurname();
String password = customer.getPassword();
double wallet = customer.getWallet();
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Detalles del cliente</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <style>
        .card-body {
          white-space: nowrap;
        }
      </style>
</head>
<body>
  <h1 class="text-center">Detalle del cliente</h1>
  <div class="container-fluid d-flex align-items-center justify-content-center vh-100">
    <div class="card">
      <div class="card-body">
        <div class="row">
          <div class="col-md-6">
            <h5>ID:</h5>
          </div>
          <div class="col-md-6">
            <h5><%= id %></h5>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <h5>Nombre: </h5>
          </div>
          <div class="col-md-6">
            <h5><%= name %></h5>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <h5>Apellido:</h5>
          </div>
          <div class="col-md-6">
            <h5><%= surname %></h5>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <h5>DNI:</h5>
          </div>
          <div class="col-md-6">
            <h5><%= customerDni %></h5>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <h5>Password:</h5>
          </div>
          <div class="col-md-6">
            <h5><%= password %></h5>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <h5>Dinero $:</h5>
          </div>
          <div class="col-md-6">
            <h5><%= wallet %></h5>
          </div>
        </div>
            <form action="delete-customer" method="POST">
                      <input type="hidden" name="customerDni" value="<%= customerDni %>">
                      <button type="submit" class="btn btn-primary">Borrar</button>
            </form>
        </div>
      </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
</body>
</html>