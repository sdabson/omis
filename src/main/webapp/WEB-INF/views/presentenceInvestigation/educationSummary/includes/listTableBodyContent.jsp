<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.educationSectionSummary">
<c:forEach var="summary" items="${summaries}">
<tr>
	<td>
		<fmt:formatDate value="${summary.attendenceStartDate}" pattern="MM/dd/yyyy"/>
		<c:if test="${not empty summary.attendenceEndDate}">
			<c:out value=" - " />
		</c:if>
		<fmt:formatDate value="${summary.attendenceEndDate}" pattern="MM/dd/yyyy"/>
	</td>
	<td>
		<c:out value="${summary.instituteCategory}"/>
	</td>
	<td>
		<c:out value="${summary.instituteName}"/>
	</td>
	<td>
		<c:choose>
			<c:when test="${summary.graduated}">
				<fmt:message key="yesLabel" bundle="${commonBundle}"/>
			</c:when>
			<c:otherwise>
				<fmt:message key="noLabel" bundle="${commonBundle}"/>
			</c:otherwise>
		</c:choose>
	</td>
</tr>
</c:forEach>
</fmt:bundle>