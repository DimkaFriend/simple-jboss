<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="customTags"%>

<customTags:page bodyClass="showBook" title="${product.title}">
	<article id="${product.title}" itemscope itemtype="http://schema.org/Book">
		
		<header id="product-highlight" class="clearfix">
			<div id="product-overview" class="container">
				<img itemprop="image" width="280px" height="395px" src=''
									  class="product-featured-image" alt="${product.title}">
				<h1 class="product-title" itemprop="name">${product.title}</h1>
				<p class="product-author">
					<span class="product-author-link"> ${product.title} </span>
				</p>

				<p itemprop="description" class="book-description">
				${product.description}
				 See the complete <a href="${product.summaryPath}" target="_blank">summary</a> of the book !
				</p>
			</div>
		</header>

		<section class="buy-options clearfix">
			<form:form servletRelativeAction="/shopping" cssClass="container">
				<input type="hidden" value="${product.id}" name="productId"/>
				<ul id="variants" class="clearfix">
					<c:forEach items="${product.prices}" var="price">
						<li class="buy-option">
							<input type="radio" name="bookType" class="variant-radio" 
									id="${product.id}-${price.bookType}"
									value="${price.bookType}" ${price.bookType.name() == 'COMBO' ? 'checked' : ''}>	 
							<label  class="variant-label" for="${product.id}-${price.bookType}"> 
								${price.bookType}								
							</label> 
							<p class="variant-price">${price.value}</p>
						</li>
					</c:forEach>
				</ul>
				<input type="submit" class="submit-image icon-basket-alt" alt="Buy now"
										title="Buy now '${product.title}'!" value="Buy"/>
			</form:form>
		</section>

		<div class="container">
			<section class="author product-detail" itemprop="author" itemscope 
																	 itemtype="http://schema.org/Person">
				<h2 class="section-title" itemprop="name">${product.title}</h2>
				<span itemprop="description">
					<p class="book-description">${product.description}</p>
				</span>
			</section>
			<section class="data product-detail">
				<h2 class="section-title">About the book:</h2>
				<p>
					Number of Pages: <span itemprop="numberOfPages">${product.pages}</span>
				</p>
				<p></p>
				<p>
					Did you find any error? <a href='/submissao-errata' target='_blank'>Tell us</a>
				</p>
			</section>
		</div>


	</article>

</customTags:page>