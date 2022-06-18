
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<header id="layout-header">
	<div class="clearfix container">
		<a href="/" id="logo"> </a>
		<div id="header-content">
			<nav id="main-nav">
				<!-- Show this piece of code only if the condition was met. The access attribute receives
				some supported expressions through Spring Security. Another expression very common is the
				hasRole(...)-->
				<sec:authorize access="isAuthenticated()">
					<!-- The tag access the object of the Spring Security that hold the user loaded in the 
					UserDetailService. This object implements Authentication interface, that has the getPrincipal
					method -->
					<sec:authentication property="principal" var="user" />
					<div>
						<b><spring:message code="users.welcome" arguments="${user.name}"/></b>
					</div>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<li><a href="${spring:mvcUrl('roleForm').build()}"><fmt:message key="register.newRole" /></a></li>
					<li><a href="${spring:mvcUrl('userForm').build()}"><fmt:message key="register.newUser" /></a></li>
					<li><a href="${spring:mvcUrl('PC#form').build()}"><fmt:message key="register.newBook" /></a></li>
				</sec:authorize>	
				<ul class="clearfix">
					<li><a href="${spring:mvcUrl('SCC#items').build()}"
						rel="nofollow"><fmt:message key="shoppingCart" /> (${sessionScope['scopedTarget.shoppingCart'].quantity})</a></li>
					<li><a href="https://github.com/EduardoGeralde" rel="nofollow"><fmt:message key="shoppingCart.about" /></a></li>
					<li><a href="https://github.com/EduardoGeralde" rel="nofollow"><fmt:message key="shoppingCart.frequent" /></a></li>
					<li><a href="<c:url value="/products?locale=en_US"/>"><fmt:message key="shoppingCart.english" /></a></li>
					<li><a href="<c:url value="/products?locale=pt"/>"><fmt:message key="shoppingCart.portuguese" /></a></li>
				</ul>
			</nav>
		</div>
	</div>
</header>
<nav class="categories-nav">
	<ul class="container">
		<li class="category"><a href="https://github.com/EduardoGeralde">Home</a>
		<li class="category"><a href="https://github.com/EduardoGeralde">
			<fmt:message key="navigation.category.agile" /> 
			<spring:message code="navigation.category.agile" /></a>
		<li class="category"><a href="https://github.com/EduardoGeralde">
			<fmt:message key="navigation.category.front" /></a>
		<li class="category"><a href="https://github.com/EduardoGeralde">
			<fmt:message key="navigation.category.games" /></a>
		<li class="category"><a href="https://github.com/EduardoGeralde"> 
			<fmt:message key="navigation.category.java" /></a>
		<li class="category"><a href="https://github.com/EduardoGeralde">
				<fmt:message key="navigation.category.mobile" /></a>
		<li class="category"><a href="https://github.com/EduardoGeralde"> 
				<fmt:message key="navigation.category.web" /></a>
		<li class="category"><a href="https://github.com/EduardoGeralde"> 
				<fmt:message key="navigation.category.others" /></a>
	</ul>
</nav>