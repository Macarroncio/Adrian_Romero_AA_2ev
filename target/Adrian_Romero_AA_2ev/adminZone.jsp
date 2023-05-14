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

</head>
<body>
    <h2>Hello admin!</h2>
    <p><a href="addTravel.jsp">Add Travel</a></p>
    <p><a href="modifyTravel.jsp">Modify Travel</a></p>
    <p><a href="customerList.jsp">See all customers</a></p>
    <p><a href="/css/default.asp">Delete Travel</a></p>
    <p><a href="html_images.asp">Delete customer</a></p>
</body>
</html>
