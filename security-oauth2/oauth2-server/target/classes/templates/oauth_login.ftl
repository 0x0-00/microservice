<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
   <!--这里是一个坑，input的name名字要是声明为username password，否则security获取不到-->
    <h1>welcome</h1>
     <form action="/auth/authorize" method="post">
         <input type="text" name="username" id="" class="loginName">
         <input type="text" name="password" id="" class="password">
         <input type="submit" value="登录">
     </form>
</body>
</html>