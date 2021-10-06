<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>LOG</title>
</head>
<body>
<p>Log in</p>

<form action="login" method="POST">
    Login: <input type="text" name="login"/> ${loginError}
    Password: <input type="password" name="pass"/> ${passError}
    <input type="submit" value="Sign in">
</form>
</body>
</html>