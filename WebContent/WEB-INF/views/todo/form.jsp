<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" 
	prefix="form" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" href="resources/css/todo.css" rel="stylesheet" />
<title>Add Todos Page</title>
</head>
<body>
	<h3>Add todos:</h3>
	<form:errors path="todo.description"/>
	<form action="addTodo" method="post">
		Description: 
		<br/>
		<textarea name="description" rows="5" cols="100"></textarea>
		<br/> 
		<input type="submit" value="Add">
	</form>
</body>
</html>