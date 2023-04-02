<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Добро пожаловать в игру Quiz" %>
</h1>
<br/>
<form action="start" method="get">
<input type="submit" name="item" value="История">
    <input type="submit" name="item" value="Спорт">
    <input type="submit" name="item" value="Поп-культура">
    <input type="submit" name="item" value="Космос">

</form>
</body>
</html>