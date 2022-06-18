<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<footer id="layout-footer">
		<div class="clearfix container">
			<nav class="breadcrumbs" itemprop="breadcrumb" xmlns:v="http://rdf.data-vocabulary.org/#"></nav>
			<div id="collections-footer">
				<!-- cdc-footer -->
				<p class="footer-title"><fmt:message key="programming.collection" /></p>
				<ul class="footer-text-links">
					<li><a href="https://github.com/EduardoGeralde"><fmt:message key="programming.collection.java" /></a></li>
					<li><a href="https://github.com/EduardoGeralde"><fmt:message key="programming.collection.web" /></a></li>
					<li><a href="https://github.com/EduardoGeralde"><fmt:message key="programming.collection.mobile" /></a></li>
					<li><a href="https://github.com/EduardoGeralde"><fmt:message key="programming.collection.games" /></a></li>
					<li><a href="https://github.com/EduardoGeralde"><fmt:message key="programming.collection.frontEnd" /></a></li>
				</ul>
				<p class="footer-title"><fmt:message key="another.subjects" /></p>
				<ul class="footer-text-links">
					<li><a href="https://github.com/EduardoGeralde"><fmt:message key="programming.collection.agile" /></a></li>
					<li><a href="https://github.com/EduardoGeralde"><fmt:message key="programming.collection.others" /></a></li>
				</ul>
			</div>
			<div id="social-footer">
				<!-- books-footer -->
				<p class="footer-title"><fmt:message key="links.publicator" /></p>
				<ul class="footer-text-links">
					<li><a href="https://github.com/EduardoGeralde" rel="nofollow"><fmt:message key="links.publicator.myEbooks" /></a></li>
					<li><a href="https://github.com/EduardoGeralde"><fmt:message key="links.publicator.about" /></a></li>
					<li><a href="https://github.com/EduardoGeralde"><fmt:message key="links.publicator.frequentQuestions" /></a></li>
				</ul>
				<p class="footer-title"><fmt:message key="social.links" /></p>
				<ul>
					<li class="social-links">
						<a href="https://github.com/EduardoGeralde" target="_blank" 
																		id="twitter" rel="nofollow">Facebook</a>
 						<a href="https://github.com/EduardoGeralde" target="_blank" 
 																		id="facebook" rel="nofollow">Twitter</a>
 					</li>
				</ul>
			</div>
			<div id="newsletter-footer">
				<!-- social-footer -->
				<p class="footer-title"><fmt:message key="receive.news" /></p>
				<div id="form-newsletter">
					<form
						action="https://docs.google.com/spreadsheet/formResponse?formkey=dFhxZ2tDalFiclU4T2FLZVY4UXVUc2c6MQ&embedded=true&ifq"
						method="POST" id="ss-form" class="form-newsletter">
						<ul>
							<li>
								<input type="hidden" name="pageNumber" value="0">
								<input type="hidden" name="backupCache" value=""> 
								<input type="email" name="entry.0.single" value="" class="ss-q-short"
																id="entry_0" placeholder="email@email.com">
							</li>
							<li>
								<input type="submit" name="submit" value="<fmt:message key="want.receive" />" id="submit-newsletter">
							</li>
						</ul>
					</form>
					<ul>
						<li class="ie8">
						<a href="https://docs.google.com/spreadsheet/viewform?formkey=dFhxZ2tDalFiclU4T2FLZVY4UXVUc2c6MQ&ifq"
												rel="nofollow"><fmt:message key="receive.news" /></a>
						</li>
					</ul>
				</div>
				<ul class="footer-payments">
					<li><img class="accepts-paypal"
						src="//cdn.shopify.com/s/files/1/0155/7645/t/177/assets/accepts_paypal.png?58522"
						border="0" alt="Paypal" width="130" height="80" /></li>
					<li><img class="accepts-pagseguro"
						src="//cdn.shopify.com/s/files/1/0155/7645/t/177/assets/logopagseguro.png?58522"
						border="0" alt="PagSeguro" width="180" height="26" /></li>
				</ul>
			</div>
		</div>
	</footer>