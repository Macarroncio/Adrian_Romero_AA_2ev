<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <h2>Welcome new customer. Please complete all fields</h2>
        <form method="post" action="RegisterCustomerServlet">
            <div class="mb-2">
                <label for="name" class="form-label">Name</label>
                <input name="name" type="text" class="form-control w-25" id="name">
            </div>
            <div class="mb-3">
                <label for="surname" class="form-label">Surname</label>
                <input name="surname" type="text" class="form-control w-25" id="surname">
            </div>
            <div class="mb-2">
                <label for="dni" class="form-label">DNI (8 characters)</label>
                <input name="dni" type="text" class="form-control w-25" id="dni">
            </div>
            <div class="mb-2">
                <label for="password" class="form-label">Password</label>
                <input name="password" type="text" class="form-control w-25" id="password">
            </div>
            <div class="mb-2">
                <label for="wallet" class="form-label">Current money (up to 9999.99)</label>
                <input name="wallet" type="text" class="form-control w-25" id="wallet">
            </div>
            <button type="submit" class="btn btn-primary">Registrar</button>
            <a href="login.jsp">Back to login page</a>
        </form>
        <div id="result"></div>
    </div>
</body>
</html>
