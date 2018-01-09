<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.visitation.msgs.visitation">
	<fmt:formatDate value="${date}" pattern="MM/dd/yyyy" var="date" scope="request"/>
	<ul>
		<c:if test="${not empty facility and not empty date}">
			<li><a class="checkOutLink" href="${pageContext.request.contextPath}/visitation/facility/checkOutAllByFacility.html?&&facility=${facility.id}&&date=${date}"><fmt:message key="checkOutAllLabel"/></a></li>
		</c:if>
	</ul>
</fmt:bundle>