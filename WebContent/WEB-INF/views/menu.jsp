<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" href="resources/css/todo.css" rel="stylesheet" />
<title>Menu</title>
</head>
<body>
	<h2>Menu</h2> <a style="padding-left: 50%;" href="doLogout"> Logout </a>
	<p>Welcome, ${userLogged.login}</p> 
	<a href="listTodos">List Todos</a>
	
</body>
</html>