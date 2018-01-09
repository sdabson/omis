<%--
 - List table body content for grievances.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:forEach var="grievanceSummary" items="${grievanceSummaries}">
	<tr>
		<td>
			<a class="actionMenuItem" href="${pageContext.request.contextPath}/grievance/grievancesActionMenu.html?grievance=${grievanceSummary.id}"></a>
		</td>
		<td>
			<c:out value="${grievanceSummary.grievanceNumber}"/>
		</td>
		<c:if test="${empty offender}">
		<td>
			<c:set var="offenderSummary" value="${grievanceSummary}" scope="request"/>
			<jsp:include page="/WEB-INF/views/offender/includes/offenderSummary.jsp"/>
		</td>
		</c:if>
		<td>
			<fmt:formatDate value="${grievanceSummary.openedDate}" pattern="MM/dd/yyyy"/>
		</td>
		<td>
			<c:out value="${grievanceSummary.subjectName}"/>
		</td>
		<td>
			<c:out value="${grievanceSummary.statusName}"/>
		</td>
		<td>
			<c:out value="${grievanceSummary.description}"/>
		</td>
	</tr>
</c:forEach>