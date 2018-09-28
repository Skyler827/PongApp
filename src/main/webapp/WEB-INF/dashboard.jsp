<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dashboard</title>
</head>
<body>
<h1>Dashboard | <form id="logout" method="POST" action="/logout"><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/><input class="linkbutton" type="submit" value="Logout" /></form></h1>
<a href="/creategame">Go to game</a>
</body>
</html>