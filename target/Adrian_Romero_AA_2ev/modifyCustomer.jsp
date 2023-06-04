<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>

<%@ page import="dao.Database" %>
<%@ page import="dao.CustomerDao" %>
<%@ page import="domain.Customer" %>
<%@ page import="java.util.Optional" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="domain.Customer" %>

<%
     Customer currentUser = (Customer) session.getAttribute("currentUser");
           if (currentUser == null) {
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
                $.post("modify-customer", formValue, function(data) {
                    $("#result").html(data);
                });
            });
        });
    </script>
    <div class="container">
        <h2>Modify Customer</h2>
        <form>
            <div class="mb-2">
                <label for="newDni" class="form-label">New DNI</label>
                <input name="newDni" type="text" class="form-control w-25" id="newDni">
            </div>
            <div class="mb-2">
                <label for="newName" class="form-label">New Name</label>
                <input name="newName" type="text" class="form-control w-25" id="newName">
            </div>
            <div class="mb-3">
                <label for="newSurname" class="form-label">New Surname</label>
                <input name="newSurname" type="text" class="form-control w-25" id="newSurname" >
            </div>
            <div class="mb-3">
                <label for="newPassword" class="form-label">New Password</label>
                <input name="newPassword" type="text" class="form-control w-25" id="newPassword" >
            </div>
            <button type="submit" class="btn btn-primary">Add</button>
        </form>
        <div id="result"></div>
    </div>
</body>
</html>