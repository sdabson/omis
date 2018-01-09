<%--
 - Author: Stephen Abson
 - Author: Joel Norris
 - Author: Ryan Johns
 - Version: 0.1.2 (Apr 19, 2016)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.courtcase.msgs.courtCase">
<c:forEach var="courtCaseSummary" items="${courtCaseSummaries}">
	<tr>
		<td>
			<a class="actionMenuItem" href="${pageContext.request.contextPath}/courtCase/courtCaseListItemActionMenu.html?courtCase=${courtCaseSummary.courtCaseId}&amp;convictionCount=${courtCaseSummary.convictions}"></a>
		</td>
		<td>
			<c:out value="${courtCaseSummary.docket}"/>
		</td>
		<td>
			<c:out value="${courtCaseSummary.courtName}"/>
		</td>
		<td>
			<c:if test="${courtCaseSummary.judge}">
				<c:out value="${courtCaseSummary.judgeLastName}"/>,
				<c:out value="${courtCaseSummary.judgeFirstName}"/>
			</c:if>
		</td>
		<td>
			<c:choose>
				<c:when test="${courtCaseSummary.youthTransfer}">
					<fmt:message key="yesLabel" bundle="${commonBundle}"/>
				</c:when>
				<c:otherwise>
					<fmt:message key="noLabel" bundle="${commonBundle}"/>
				</c:otherwise>
			</c:choose>
		</td>
		<td>
			<fmt:formatDate value="${courtCaseSummary.pronouncementDate}" pattern="MM/dd/yyyy"/>
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
</fmt:bundle>