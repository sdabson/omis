<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<fmt:bundle basename="omis.visitation.msgs.visitation">
<c:forEach var="visitSummary" items="${visitSummaries}" varStatus="status">
	<tr class="visitSummary" id="visitSummary">
		<td>
			<a class="actionMenuItem visitRowActionMenu" id="visitRowActionMenuLink${visitSummary.visitId}" href="${pageContext.request.contextPath}/visitation/facility/visitLogRowActionMenu.html?visit=${visitSummary.visitId}&currentlyVisiting=${visitSummary.currentlyVisiting}&&facility=${facility.id}&&date=${date}"></a>
		</td>
		<td>
			<c:choose>
				<c:when test="${visitSummary.currentlyVisiting}">
					<fmt:message key="visitInProgress"/>
				</c:when>
				<c:otherwise>
					<fmt:message key="visitComplete"/>
				</c:otherwise>
			</c:choose>
		</td>
		<td>
			<c:out value="${visitSummary.lastName}"/>
		</td>
		<td>
			<c:out value="${visitSummary.firstName}"/>
		</td>
		<td>
			<fmt:formatDate value="${visitSummary.date}"/>
		</td>
		<td>
			<c:out value="${visitSummary.badgeNumber}"/>
		</td>
	</tr>
</c:forEach>
</fmt:bundle>
