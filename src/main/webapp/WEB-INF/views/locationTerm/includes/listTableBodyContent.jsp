<%--
 - Table body of location terms.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.locationterm.msgs.locationTerm" var="locationTermBundle"/>
<c:forEach var="locationTermSummary" items="${locationTermSummaries}">
	<tr>
		<td>
			<a href="${pageContext.request.contextPath}/locationTerm/locationTermsActionMenu.html?locationTerm=${locationTermSummary.id}" class="actionMenuItem"></a>
		</td>
		<td><c:out value="${locationTermSummary.locationName}"/></td>
		<td>
			<c:choose>
				<c:when test="${not empty locationTermSummary.singleReasonName}">
					<c:out value="${locationTermSummary.singleReasonName}"/>
				</c:when>
				<c:otherwise>
					<fmt:message key="reasonCountLabel" bundle="${locationTermBundle}">
						<fmt:param value="${locationTermSummary.reasonCount}"/>
					</fmt:message>
				</c:otherwise>
			</c:choose>
		</td>
		<td><fmt:formatDate value="${locationTermSummary.startDate}" pattern="MM/dd/yyyy h:mm a"/></td>
		<td><fmt:formatDate value="${locationTermSummary.endDate}" pattern="MM/dd/yyyy h:mm a"/></td>
		<td><c:out value="${locationTermSummary.dayCount}"/></td>
	</tr>
</c:forEach>