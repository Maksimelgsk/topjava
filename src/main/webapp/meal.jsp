
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
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Edit meal</h2>
<hr>
<form method="post" action="${meal == null ? '?action=create' : '?action=update&id=' += meal.id}">
    <dl>
        <dt>Date time</dt>
        <dd><label>
            <input type="datetime-local" name="datetime" value="${meal.dateTime}" required>
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
