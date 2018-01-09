<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.courtcasecondition.msgs.courtCaseConditions">
	<ul>
		<sec:authorize access="hasRole('COURT_CASE_CONDITION_LIST') or hasRole('ADMIN')">
			<li>
				<a class="listLink" href="${pageContext.request.contextPath}/courtCaseCondition/list.html?offender=${offender.id}"><span class="visibleLinkLabel"><fmt:message key="listCourtCaseConditionsLink"/></span></a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>