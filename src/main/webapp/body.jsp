<%--
  Created by IntelliJ IDEA.
  User: julia
  Date: 02.04.2023
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Game</title>
    <script>
        function check(a) {
            let b = document.getElementById(a);
            const items = b.querySelectorAll('input[name="answ"]');
            for (const item of items) {
                if (item.checked) {
                    if (item.value === 'true') {
                        document.getElementsByName(a).values().next().value.innerHTML = "<span style='color: green;'>Правильный ответ</span>";
                        ;
let v=document.getElementById('tra').textContent;
v++;
document.getElementById('tra').innerText=v;
                    } else {
                        document.getElementsByName(a).values().next().value.innerHTML = "<span style='color: red;'>Неправильный ответ</span>";
                        ;
                        let g=document.getElementById('wra').textContent;
                        g++;
                        document.getElementById('wra').innerText=g;

                    }
                }
            }
            return false;
        }
    </script>
    <style></style>
</head>
<body>
<h2>Правильных ответов :</h2>
<span id="tra">0  </span>
<h2>Неравильных ответов :</h2>
<span id="wra">0  </span>
<ol>
    <c:forEach items="${questionList}" var="question">
        <li>${question.text}</li>
        <hr>
        <form id='${question.text}' onsubmit="check('${question.text}');return false;">
            <ul>
                <c:forEach items="${question.answers}" var="answer">
                    <li><input type="radio" id="${answer.text}" name="answ" value="${answer.ok}">${answer.text}  </li>
                    <br>
                </c:forEach>
            </ul>
            <input type="submit" value="ответ">

        </form>
        <p class="hid" name="${question.text}"></p>
    </c:forEach>
</ol>

</body>
</html>
