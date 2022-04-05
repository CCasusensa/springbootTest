<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登入成功</title>
</head>
<body>
${username}，您已登入成功！</br>
${errormsg}</br>
<form action = "/modifyUser" method="post">
    操作介面:</br>
    帳號：<input type="text" id="username" name = "username"> <br>
    密碼：<input type="password" id = "password" name = "password"> <br>
    新密碼：<input type="password" id = "password" name = "newPassword"><br><br>
    <input type="submit" name="addUser" value="新增使用者">
    <input type="submit" name="delUser" value="刪除使用者">
    <input type="submit" name="modifyUser" value="修改使用者密碼">
</form>
</br>
<form action="logout" method="POST">
    <input type="submit" value="登出會員">
</form>
</body>
</html>