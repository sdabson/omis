<%--
  - List table body content.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<c:forEach var="courtCaseSummary" items="${courtCaseSummaries}">
	<tr>
		<td><a href="${pageContext.request.contextPath}/offenseTerm/offenseTermsActionMenu.html?courtCase=${courtCaseSummary.courtCaseId}" class="actionMenuItem"></a></td>
		<td><c:out value="${courtCaseSummary.docket}"/></td>
		<td><c:out value="${courtCaseSummary.courtName}"/></td>
		<td><c:if test="${courtCaseSummary.judge}"><c:out value="${courtCaseSummary.judgeLastName}"/>, <c:out value="${courtCaseSummary.judgeFirstName}"/></c:if></td>
		<td>
			<c:choose>
				<c:when test="${courtCaseSummary.convictionOverturned}">
					<fmt:message key="yesLabel" bundle="${commonBundle}"/>
				</c:when>
				<c:otherwise>
					<fmt:message key="noLabel" bundle="${commonBundle}"/>
				</c:otherwise>
			</c:choose>
		</td>
		<td>
			<c:choose>
				<c:when test="${courtCaseSummary.dismissed}">
					<fmt:message key="yesLabel" bundle="${commonBundle}"/>
				</c:when>
				<c:otherwise>
					<fmt:message key="noLabel" bundle="${commonBundle}"/>
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
</c:forEach>