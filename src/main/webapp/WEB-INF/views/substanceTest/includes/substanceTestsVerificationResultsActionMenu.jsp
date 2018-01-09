<!-- 
 - Author: Joel Norris
 - Version: 0.1.0 (June 30, 2015)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.substance.msgs.substance">
	<ul>
		<li>
			<a href="${pageContext.request.contextPath}/substanceTest/addLabResult.html" class="createLink" id="addLabResultLink"><span class="visibleLinkLabel"><fmt:message key="addLabResultLink"/></span></a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/substanceTest/verifyLocalResults.html" class="createLink" id="verifyLocalResults"><span class="visibleLinkLabel"><fmt:message key="verifyLocalResultsLink"/></span></a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/substanceTest/verifyLocalResults.html" class="createLink" id="verifyLocalPositiveResults"><span class="visibleLinkLabel"><fmt:message key="verifyLocalPositiveResultsLink"/></span></a>
		</li>
	</ul>
</fmt:bundle>