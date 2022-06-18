<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Registration</title>
</head>
<body>
	<form:form servletRelativeAction="${spring:mvcUrl('saveUser').build()}" method="post" commandName="users">
	
		<div>
			<label for="name">User name</label>
			<form:input path="name"/>
			<form:errors path="name"/>
		</div>
		
		<div>
			<label for="login">Login</label>
			<form:input path="login"/>
			<form:errors path="login"/>
		</div>
		
		<div>
			<label for="password">Password</label>
			<form:input path="password"/>
			<form:errors path="password"/>
		</div>
		
		<div>
			<form:checkboxes path="roles" items="${roleList}" itemLabel="name" itemValue="name"/>
		</div>
		
		<div>
			<input type="submit" value="Save">
		</div>
		
	</form:form>
</body>
</html>