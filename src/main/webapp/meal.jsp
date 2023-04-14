<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Meal</title>
    <style>
        dl {
            background: none repeat scroll 0 0 white;
            margin: 8px 0;
            padding: 0;
        }
        dt {
            display: inline-block;
            width: 120px;
        }
        dd {
            display: inline-block;
            margin-left: 8px;
            vertical-align: top;
        }
        input
        {
            font-family: Arial, serif;
        }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2><c:out value="${not empty meal.id ? 'Edit meal' : 'Add meal'}"/></h2>
<form method="post" action='meals' name="frmAddMeal">
    <input type="hidden" name="id" value="${meal.id}"/>
    <dl>
        <dt>Date time</dt>
        <dd><label>
    <input type="datetime-local" name="dateTime"
    <fmt:parseDate value="${meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDate" type="date"/>
    <fmt:formatDate value="${parsedDate}" var="formattedDate" type="both" pattern="yyyy-MM-dd HH:mm"/>
           value="${formattedDate}" required/>
        </label></dd>
    </dl>
    <dl>
        <dt>Description</dt>
        <dd><label>
            <input type="text" name="description" value="${meal.description}" size="30" required>
        </label></dd>
    </dl>
    <dl>
        <dt>Calories</dt>
        <dd><label>
            <input type="number" name="calories" value="${meal.calories}" required>
        </label></dd>
    </dl>
    <button type="submit">Save</button>
    <button onclick="window.history.back()" type="button">Cancel</button>
</form>
</body>
</html>
