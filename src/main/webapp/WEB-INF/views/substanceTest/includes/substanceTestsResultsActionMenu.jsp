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
		<sec:authorize access="hasRole('SUBSTANCE_TEST_EDIT') or hasRole('ADMIN')">
			<li>
				<a href="${pageContext.request.contextPath}/substanceTest/addSubstanceTestResult.html?substanceTestResultIndex=${currentSubstanceTestResultIndex}" class="createLink" id="addSubstanceTestResultLink"><span class="visibleLinkLabel"><fmt:message key="addSubstanceTestResultLink"/></span></a>
			</li>
		</sec:authorize>
		<li></li>
		<li></li>
	</ul>
</fmt:bundle>