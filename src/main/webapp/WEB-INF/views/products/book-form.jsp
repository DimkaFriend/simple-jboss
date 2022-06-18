<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Book Registration</title>
</head>
<body>

	<!-- name attribute receive the attribute type, with the first letter in lowercase
	<spring:hasBindErrors name="product">
		<ul>
			<c:forEach items="${errors.allErrors}" var="error">
				<li><spring:message code="${error.code}" text="${error.defaultMessage}"/></li>
			</c:forEach>
		</ul>
	</spring:hasBindErrors>
	-->

	<!-- commandName receives the type of the parameter that has to be validate, and accessed 
	by the path, with first letters lower case -->
	<form:form servletRelativeAction="${spring:mvcUrl('saveProduct').build()}" method="post" 
																				commandName="product"
																				enctype="multipart/form-data">
	
		<div>
			<label for="subject">Book Subject</label>
			<form:input path="subject"/>
			<form:errors path="subject"/>
		</div>	
	
		<div>
			<label for="title">Title</label>
			<form:input path="title"/>
			<form:errors path="title"/>
		</div>
		
		<div>
			<label for="author">Author</label>
			<form:input path="author"/>
			<form:errors path="author"/>
		</div>
		
		<div>
			<label for="description">Description</label>
			<form:textarea path="description" rows="10" cols="20"/>
			<form:errors path="description"/>
		</div>
		
		<div>
			<label for="pages">Pages Number</label>
			<form:input path="pages"/>
			<form:errors path="pages"/>
		</div>
		
		<div>
			<label for="company">Publishing Company</label>
			<form:input path="company"/>
			<form:errors path="company"/>
		</div>
		
		<div>
			<label for="releaseDate">Release Date</label>
			<form:input path="releaseDate" type="date"/>
			<form:errors path="releaseDate"/>
		</div>
		
		<div>
			<label for="summary">Book Summary</label>
			<input type="file" name="summary"/>
			<form:errors path="summaryPath"/>
		</div>
		
		<div>
			<c:forEach items="${types}" var="bookType" varStatus="status">
				<div>
					<label for="price_${bookType}">${bookType}</label>
					<input type="text" name="prices[${status.index}].value" id="price_${bookType}"/>
					<input type="hidden" name="prices[${status.index}].bookType" value="${bookType}"/>
				</div>
			</c:forEach>
		</div>
		
		<div>
			<input type="submit" value="Save">
		</div>
	</form:form>

</body>
</html>