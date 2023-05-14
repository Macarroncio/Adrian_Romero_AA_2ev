<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>

<%@ page import="dao.Database" %>
<%@ page import="dao.TravelDao" %>
<%@ page import="domain.Travel" %>
<%@ page import="java.util.Optional" %>
<%@ page import="java.sql.SQLException" %>
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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
    <script type="text/javascript">
        $(document).ready(function() {
            $("form").on("submit", function(event) {
                event.preventDefault();
                var formValue = $(this).serialize();
                $.post("AddTravel", formValue, function(data) {
                    $("#result").html(data);
                });
            });
        });
    </script>
    <div class="container">
        <h2>Register new travel</h2>
        <form>
            <div class="mb-2">
                <label for="destination" class="form-label">Destination</label>
                <input name="destination" type="text" class="form-control w-25" id="destination">
            </div>
            <div class="mb-3">
                <label for="price" class="form-label">Price (max value 9999.99)</label>
                <input name="price" type="text" class="form-control w-25" id="price" >
            </div>
            <button type="submit" class="btn btn-primary">Add</button>
        </form>
        <div id="result"></div>
    </div>
</body>
</html>