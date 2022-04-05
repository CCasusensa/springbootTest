<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>test Login</title>
</head>
<body>
${errormsg}</br>
    <form action = "/login" method="post">
        帳號：<input type="text" id="username" name = "username"> <br>
        密碼：<input type="password" id = "password" name = "password"> <br>
        <input type="submit" id = "login" value="登入">
    </form>
<a href="/index">回到首頁</a>
</body>
</html>