<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" href="resources/css/todo.css" rel="stylesheet" />

<link rel="stylesheet" href="resources/css/jquery-ui.css">
<script src="resources/js/jquery.js"></script>
<script src="resources/js/jquery-ui.js"></script>

<script>
  $( function() {
    $( "#finishDate" ).datepicker({dateFormat: 'dd/mm/yy'});
  } );
</script>
<title>Update Todo</title>
</head>
<body>

	<h3>Update todo - ${todo.id}</h3>
	<form action="updateTodo" method="post">
	
		<input type="hidden" name="id" value="${todo.id}" /> Description:<br />
		
		<textarea name="description" cols="100" rows="5">${todo.description}</textarea>
		
		<br/> 
		Finished? <input type="checkbox" name="finished"
						 value="true" ${todo.finished? 'checked' : '' } /> 
		<br/>
		<br/> <input type="text" id="finishDate" name="finishDate"
		      value="<fmt:formatDate value="${todo.finishDate.time}" pattern="dd/MM/yyyy" />" />
					  
		<br/> <input type="submit" value="Save" />
	</form>
	
</body>

</html>