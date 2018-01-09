<%--
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.religion.msgs.religion" var="religionBundle"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<table class="listTable">
	<thead>
		<tr>
			<th class="operations"></th>
			<th><fmt:message key="religionLabel" bundle="${religionBundle}"></fmt:message>
			<th class="date"><fmt:message key="startDateLabel" bundle="${commonBundle}"></fmt:message>
			<th class="date"><fmt:message key="endDateLabel" bundle="${commonBundle}"></fmt:message>
			<c:forEach var="accommodationName" items="${accommodationNames}">
			<th><c:out value="${accommodationName}"/></th>
			</c:forEach>
			<th><fmt:message key="approvedLabel" bundle="${religionBundle}"/></th>
		</tr>
	</thead>
	<tbody id="preferences">
		<jsp:include page="listTableBodyContent.jsp"/>
	</tbody>
</table>