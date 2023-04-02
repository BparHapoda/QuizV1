<%--
  Created by IntelliJ IDEA.
  User: julia
  Date: 31.03.2023
  Time: 23:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Page</title>
    <style>
        #answers{display: none;}
    </style>
    <script>
        function change(){
            let elem=document.getElementById('answers');
            if (elem.style.display==="block")
            {elem.style.display="none";}
            else {elem.style.display="block";}
        }
    </script>
</head>
<body>
<form action="admin" method="post">

    <br>
    Tema :
    <br>
    <select name="theme" id="theme">
        <option value="История">История</option>
        <option value="Спорт">Спорт</option>
        <option value="Поп-культура">Поп-культура</option>
        <option value="Космос">Космос</option>
    </select>
    <br>
    <label for="question">Вопрос </label>
    <input type="text" id="question" name="question" value="">
    <br>
    Добавить варианты ответов ?
    <input type="checkbox" name="withAnswers" onchange="change()">
    <div id="answers" >
    Ответы :
    <br>
    <ol>
        <li>
            <input type="text" id="answer1" name="answer1" value="">
            <input type="checkbox" name="tr" id="tr1" value="a">

        </li>

        <li>
            <input type="text" id="answer2" name="answer2" value="" checked="false">
            <input type="checkbox" name="tr" id="tr2" value="b">

        </li>

        <li>
            <input type="text" id="answer3" name="answer3" value="" checked="false">
            <input type="checkbox" name="tr" id="tr3" value="c">

        </li>
    </ol>
    </div>
    <input type="submit" value="submit"/>
</form>
</body>
</html>
