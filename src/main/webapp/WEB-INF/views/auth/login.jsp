<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
	<head>
		<title>Login Page</title>
	</head>
	<body>
		<h3>Please, insert your credentials</h3>
		<form:form servletRelativeAction="/login" >
			<table>
				<tr>
					<td>
						<label for="username">Login</label>
					</td>
					<td>
						<label for="password">Password</label>
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" name="username" value=""/>
					</td>
					<td>
						<input type="password" name="password"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" name="submit" value="Login"/>
					</td>
				</tr>
			</table>
		</form:form>
	</body>
</html>