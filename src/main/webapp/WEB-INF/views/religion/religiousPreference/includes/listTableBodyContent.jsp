<%--
 - Author: Stephen Abson
 --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/omis.tld" prefix="omis" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<c:forEach var="religiousPreferenceSummary" items="${religiousPreferenceSummaries}">
	<tr>
		<td>
			<a class="actionMenuItem" href="${pageContext.request.contextPath}/religion/religiousPreference/preferencesActionMenu.html?preference=${religiousPreferenceSummary.id}"></a>
		</td>
		<td><c:out value="${religiousPreferenceSummary.religionName}"/> (<c:out value="${religiousPreferenceSummary.religionGroupName}"/>)</td>
		<td><fmt:formatDate value="${religiousPreferenceSummary.startDate}" pattern="MM/dd/yyyy"/></td>
		<td><fmt:formatDate value="${religiousPreferenceSummary.endDate}" pattern="MM/dd/yyyy"/></td>
		<c:forEach var="accommodationName" items="${accommodationNames}">
		<td>
			<c:choose>
				<c:when test="${omis:contains(religiousPreferenceSummary.authorizedAccommodationNames, accommodationName)}">
					<fmt:message key="yesLabel" bundle="${commonBundle}"/>
				</c:when>
				<c:otherwise>
					<fmt:message key="noLabel" bundle="${commonBundle}"/>
				</c:otherwise>
			</c:choose>
		</td>
		</c:forEach>
		<td>
			<c:if test="${not empty religiousPreferenceSummary.approved}">
				<c:choose>
					<c:when test="${religiousPreferenceSummary.approved}">
						<fmt:message key="yesLabel" bundle="${commonBundle}"/>
					</c:when>
					<c:otherwise>
						<fmt:message key="noLabel" bundle="${commonBundle}"/>
					</c:otherwise>
				</c:choose>
			</c:if>
		</td>
	</tr>
</c:forEach>