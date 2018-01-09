<%--
  - Table body content for program placements.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:forEach var="programPlacementSummary" items="${programPlacementSummaries}">
	<tr>
		<td><a href="${pageContext.request.contextPath}/program/programPlacementsActionMenu.html?programPlacement=${programPlacementSummary.id}" class="actionMenuItem"></a></td>
		<td><c:out value="${programPlacementSummary.supervisoryOrganizationName}"/></td>
		<td><c:out value="${programPlacementSummary.correctionalStatusName}"/></td>
		<td><c:if test="${programPlacementSummary.location}"><c:out value="${programPlacementSummary.locationOrganizationName}"/></c:if></td>
		<td><c:out value="${programPlacementSummary.programName}"/></td>
		<td><fmt:formatDate pattern="MM/dd/yyyy" value="${programPlacementSummary.startDate}"/></td>
		<td><fmt:formatDate pattern="MM/dd/yyyy" value="${programPlacementSummary.endDate}"/></td>
		<td><c:out value="${programPlacementSummary.dayCount}"/></td>
	</tr>
</c:forEach>