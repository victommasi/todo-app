<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Finished Date</title>
</head>
<body>
	<td>${todo.id}</td>
	<td>${todo.description}</td>
	<td>Finished</td>
	<td><fmt:formatDate value="${todo.finishDate.time}"
			pattern="dd/MM/yyyy" /></td>
	<td><a href="showTodo?id=${todo.id}">Update</a></td>
	<td><a href="removeTodo?id=${todo.id}">Remove</a></td>
</body>
</html>