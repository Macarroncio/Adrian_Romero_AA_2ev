<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <h2>Login</h2>
        <form method="post" action="LoginServlet">
            <div class="mb-2">
                <label for="dni" class="form-label">User DNI</label>
                <input name="dni" type="text" class="form-control w-25" id="dni">
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input name="password" type="password" class="form-control w-25" id="password">
            </div>
            <button type="submit" class="btn btn-primary">Login</button>
            <br>
            <a href="registerNewCustomer.jsp">Create new account</a>
        </form>
        <div id="result"></div>
    </div>
</body>
</html>
