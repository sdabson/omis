<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.visitation.msgs.visitation">
	<fmt:formatDate value="${date}" pattern="MM/dd/yyyy" var="date" scope="request"/>
	<ul>
		<li>
			<a class="checkOutLink" href="${pageContext.request.contextPath}/visitation/facility/checkOutAllOffenderVisits.html?offender=${offender.id}&&date=${date}&&facility=${facility.id}"><fmt:message key="checkOutAllVisitsForOffender"/></a>
		</li>
	</ul>
</fmt:bundle>