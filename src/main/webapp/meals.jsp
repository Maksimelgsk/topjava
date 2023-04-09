<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Meals</title>
    <style>
        .normal{color: green}
        .exceeded{color: red}
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<section>
    <a href="meals?&action=edit">Add Meal</a>
    <p></p>
    <table>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
        <jsp:useBean id="mealList" type="java.util.List" scope="request"/>
        <c:forEach items="${mealList}" var="meal">
            <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealTo"/>
            <tr class="${!meal.excess ? 'normal':'exceeded'}">
                    <td><%=meal.getDate().toLocalDate()%> <%=meal.getDate().toLocalTime()%></td>
                    <td>${meal.description}</td>
                    <td>${meal.calories}</td>
                <td><a href="meals?&action=update">Update</a></td>
                <td><a href="meals?&action=delete">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>
