<%--
 - Author: Yidong Li
 - Version: 0.1.0 (Sept 7, 2015)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.incident.msgs.incident">
<c:forEach var="incidentStatementSummary" items="${incidentStatementSummaries}" varStatus="status">
	<tr>
		<td>
			<a class="actionMenuItem incidentStatementRowActionMenu" id="incidentStatementRowActionMenuLink${status.index}" href="${pageContext.request.contextPath}/incident/statement/incidentStatementRowActionMenu.html?incidentStatement=${incidentStatementSummary.id}"></a>
		</td>
		<td><c:out value="${incidentStatementSummary.reportNumber}"/></td>
		<td><fmt:formatDate value="${incidentStatementSummary.incidentDate}" pattern="MM/dd/yyyy"/></td>
		<td>
			<fmt:message key="submissionCategory.${incidentStatementSummary.submissionCategory}.label"/>
		</td>
		<td><c:out value="${incidentStatementSummary.reportTitle}"/></td>
		<td>
			<c:choose>
				<c:when test="${not empty incidentStatementSummary.facilityName and not empty incidentStatementSummary.locationName}">
					<fmt:message key="incidentStatementLocationInformation">
						<fmt:param value="${incidentStatementSummary.facilityName}"/>
						<fmt:param value="${incidentStatementSummary.locationName}"/>
					</fmt:message>
				</c:when>
				<c:when test="${not empty incidentStatementSummary.facilityName}">
					<c:out value="${incidentStatementSummary.facilityName}"/>
				</c:when>
				<c:otherwise>
					<c:out value="${incidentStatementSummary.locationName}"/>
				</c:otherwise>
			</c:choose>
		<td>
			<c:forEach var="involvedParty" items="${summaryMap[incidentStatementSummary]}">
				<c:out value="${involvedParty.firstName}"/> <c:out value="${involvedParty.lastName}"/><c:if test="${involvedParty.offender}"> <c:out value="${involvedParty.offenderNumber}"/></c:if><br>
			</c:forEach>
		</td>
		<td><c:out value="${incidentStatementSummary.reportingStaffFirstName}"/> <c:out value="${incidentStatementSummary.reportingStaffLastName}"/></td>
	</tr>
</c:forEach>
</fmt:bundle>