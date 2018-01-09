<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.courtcasecondition.msgs.courtCaseConditions">
	<ul>
		<li>
			<sec:authorize access="hasRole('COURT_CASE_CONDITION_EDIT') or hasRole('ADMIN')">
				<a class="viewEditLink" href="${pageContext.request.contextPath}/courtCaseCondition/condition/addSpecialGroup.html?courtCaseAgreement=${courtCaseAgreement.id}&conditionGroup=${conditionGroup.id}"><span class="visibleLinkLabel"><fmt:message key="createEditSpecialLink"><fmt:param value="${conditionGroup.name}"/></fmt:message></span></a>
			</sec:authorize>
		</li>
	</ul>
</fmt:bundle>