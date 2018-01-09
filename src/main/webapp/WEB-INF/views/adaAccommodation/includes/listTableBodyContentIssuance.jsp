<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<fmt:bundle basename="omis.adaaccommodation.msgs.adaAccommodation">
	<c:forEach var="issuance" items="${issuances}" varStatus="issuanceStatus">
	<tr id="issuanceRow${issuanceStatus.index}">
		<td>
			<a class="actionMenuItem issuanceRowActionMenuLinks" id="issuanceSummaryActionMenuLink${issuanceStatus.index}" href="${pageContext.request.contextPath}/adaAccommodation/accommodationIssuancesRowActionMenu.html?issuance=${issuance.accommodationIssuanceId}"></a>
		</td>
		<td><fmt:formatDate value="${issuance.timestamp}"  type="both" pattern="MM/dd/yyyy hh:mm a"/></td>
		<td><c:out value="${issuance.description}"/></td>
		<td><c:out value="${issuance.issuerLastName}, ${issuance.issuerFirstName}"/></td>
	</tr>
	</c:forEach>
</fmt:bundle>