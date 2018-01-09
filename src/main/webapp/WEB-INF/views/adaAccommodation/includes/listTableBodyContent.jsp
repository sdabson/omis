<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<fmt:bundle basename="omis.adaaccommodation.msgs.adaAccommodation">
	<c:forEach var="accommodation" items="${accommodationSummaries}"
		varStatus="status">
		<c:set var="accommodation" value="${accommodation}" scope="request" />
		<tr id="accommodationRow${status.index}" class="accommodationClass">
			<td><a class="actionMenuItem rowActionMenuLinks"
				id="summaryActionMenuLink${status.index}"
				href="${pageContext.request.contextPath}/adaAccommodation/accommodationsRowActionMenu.html?offender=${offender.id}&accommodationSummaries=${accommodation.accommodationId}"></a>
			</td>
			<sec:authorize
				access="hasRole('ADA_ACCOMMODATION_VIEW') or hasRole('ADMIN')">
				<td><c:out
						value="${accommodation.disabilityClassificationCategory}" /> <c:out
						value=": ${accommodation.disabilityDescription}" /></td>
			</sec:authorize>
			<td><c:out value="${accommodation.accommodationDescription}" /></td>
			<td><c:out value="${accommodation.accCategory}" /></td>
			<td><fmt:formatDate
					value="${accommodation.authorizationStartDate}"
					pattern="MM/dd/yyyy" /></td>
			<td><c:out value="${accommodation.authorizationSource}" /></td>
			<td><fmt:formatDate
					value="${accommodation.authorizationEndDate}" pattern="MM/dd/yyyy" /></td>
			<td><fmt:formatDate var="createdDate"
					value="${accommodation.createdDate}" pattern="MM/dd/yyyy" /> <fmt:message
					key="creationSignatureLabel">
					<fmt:param value="${accommodation.createdByLastName}" />
					<fmt:param value="${accommodation.createdByFirstName}" />
					<fmt:param value="${accommodation.createdByUsername}" />
					<fmt:param value="${createdDate}" />
				</fmt:message></td>
		</tr>
		<c:if test="${not empty issuances}">
			<tr>
				<td></td>
				<td colspan="7"><jsp:include page="associatedIssuancesRow.jsp" />
				</td>
			</tr>
		</c:if>
	</c:forEach>
</fmt:bundle>