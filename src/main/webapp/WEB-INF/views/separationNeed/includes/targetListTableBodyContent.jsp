<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.separationneed.msgs.separationNeed">
<c:forEach var="summary" items="${targetSeparationNeedSummaries}" varStatus="status">
	<tr>
		<td>
			<fmt:formatNumber value="${summary.subjectOffenderNumber}" pattern="######" var="subjectOffenderNumber" /> 
			<fmt:message key="offenderLastFirstName">
					<fmt:param value="${summary.subjectOffenderFirstName}"/>
					<fmt:param value="${summary.subjectOffenderLastName}"/>
			</fmt:message>
			<c:if test="${not empty summary.subjectOffenderMiddleInitial}">
				<fmt:message key="offenderMiddleInitial">
					<fmt:param value="${summary.subjectOffenderMiddleInitial}"/>
				</fmt:message>
			</c:if>
			<fmt:message key="offenderNumber">
				<fmt:param value="${subjectOffenderNumber}"/>
			</fmt:message>
		</td>
		<td>
			<c:out value="${summary.subjectOffenderHousingLocation}"/>
		</td>
		<td>
		<c:if test="${not empty summary.creationComment}">
			<a class="commentLink" title="<c:out value="${summary.creationComment}"/>"></a>
		</c:if>
		</td>
		<td>
			<fmt:formatDate pattern="MM/dd/yyyy" value="${summary.date}"/>
		</td>
		<td>
			<fmt:formatDate pattern="MM/dd/yyyy" value="${summary.removalDate}"/>
		</td>
	</tr>
</c:forEach>
</fmt:bundle>
