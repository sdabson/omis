<%--
 - Action menu for offenses.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.offenseterm.msgs.offenseTerm" var="offenseTermBundle"/>
<ul>
	<sec:authorize access="hasRole('OFFENSE_TERM_CREATE') or hasRole('ADMIN')">
		<li>
			<a href="${pageContext.request.contextPath}/offenseTerm/createOffense.html?person=${person.id}&amp;courtCase=${courtCase.id}" class="createLink" id="createOffenseLink"><span class="visibleLinkLabel"><fmt:message key="createOffenseLink" bundle="${offenseTermBundle}"/></span></a>
		</li>
	</sec:authorize>
</ul>