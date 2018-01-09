<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.visitation.msgs.visitation">
	<fmt:formatDate value="${date}" pattern="MM/dd/yyyy" var="date" scope="request"/>
	<ul>
<%-- 		<li><a class="viewEditLink" href="${pageContext.request.contextPath}/visitation/visit/edit.html?visit=${visit.id}"><label class="linkLabel"><fmt:message key="viewEditLabel"/></label></a></li> --%>
		<c:choose>
			<c:when test="${currentlyVisiting}">
				<li><a class="checkOutLink" href="${pageContext.request.contextPath}/visitation/facility/checkOut.html?visit=${visit.id}&&facility=${facility.id}&&date=${date}"><fmt:message key="checkOutLabel"/></a></li>
			</c:when>
		</c:choose>
		<li><a class="removeLink" href="${pageContext.request.contextPath}/visitation/facility/removeVisit.html?visit=${visit.id}&&facility=${facility.id}&&date=${date}"><fmt:message key="removeLabel"/></a></li>
	</ul>
</fmt:bundle>