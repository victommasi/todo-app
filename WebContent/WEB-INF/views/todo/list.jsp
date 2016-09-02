<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Todo List</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link type="text/css" href="resources/css/todo.css" rel="stylesheet" />
<link rel="stylesheet" href="resources/css/jquery-ui.css">
<script src="resources/js/jquery.js"></script>
<script src="resources/js/jquery-ui.js"></script>

<script type="text/javascript">
	function finishNow(id) {
		$.post("finishTodo", {'id' : id}, function(response){
			$("#todo_"+id).html(response);
		});
	}
	function removeNow(id) {
        $.get("removeTodo", {'id' : id}, function() {
          $("a").closest("tr").hide();
        });
    }
</script>
</head>
<body>
	<a href="newTodo">Create new todo</a> <a style="padding-left: 50%;" href="doLogout"> Logout </a>
	<br/>
	<br/>
	<table>
		<tr>
			<th>Id</th>
			<th>Description</th>
			<th>Finished?</th>
			<th>Finish Date</th>
		</tr>
		<c:forEach items="${todos}" var="todo">
			<tr id="todo_${todo.id }">
				<td>${todo.id}</td>
				<td>${todo.description}</td>
				
				<c:if test="${todo.finished eq true}">
					<td>Finished</td>
				</c:if>
				
				<c:if test="${todo.finished eq false}">
					<td>
						<a href="#" onclick="finishNow(${todo.id})"> 
							Finish now 
						</a>
					</td>
				</c:if>
				
				<td>
					<fmt:formatDate value="${todo.finishDate.time}" 
									pattern="dd/MM/yyyy" />
				</td>
						
				<td><a href="showTodo?id=${todo.id}">Update</a></td>
				<td><a href="removeTodo?id=${todo.id}">Remove</a></td>
				<!-- <td><a href="#" onClick="removeNow(${todo.id})">Delete</a></td> -->
			</tr>
		</c:forEach>
	</table>
</body>
</html>