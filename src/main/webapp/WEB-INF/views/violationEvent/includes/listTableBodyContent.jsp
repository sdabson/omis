<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.violationevent.msgs.violationEvent">
<c:forEach var="summary" items="${violationEventSummaries}">
<tr>
	<td><a class="actionMenuItem rowActionMenuItem" href="${pageContext.request.contextPath}/violationEvent/violationEventsRowActionMenu.html?violationEvent=${summary.violationEventId}"></a></td>
	<td>
		<fmt:formatDate value="${summary.date}" pattern="MM/dd/yyyy" />
	</td>
	<td>
		<c:out value="${summary.category}"/>
	</td>
	<td>
		<c:out value="${summary.jurisdiction}"/>
	</td>
	<td>
		<c:out value="${summary.violationCount}"/>
	</td>
</tr>
</c:forEach>
</fmt:bundle>	