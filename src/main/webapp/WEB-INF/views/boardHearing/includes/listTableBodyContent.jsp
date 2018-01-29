<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.hearing.msgs.hearing">
<c:forEach var="summary" items="${boardHearingSummaries}">
<tr>
	<td><a class="actionMenuItem rowActionMenuItem" href="${pageContext.request.contextPath}/boardHearing/boardHearingsRowActionMenu.html?boardHearing=${summary.boardHearingId}"></a></td>
	<td>
		<fmt:formatDate value="${summary.hearingDate}" pattern="MM/dd/yyyy" />
	</td>
	<td>
		<c:out value="${summary.hearingStatusName}"/>
	</td>
	<td>
		<c:out value="${summary.appearanceCategoryName}"/>
	</td>
	<td>
		<c:out value="${summary.hearingLocationName}"/>
	</td>
	<td>
		<c:if test="${not empty summary.hearingAnalystLastName}">
			<c:out value="${summary.hearingAnalystLastName}, ${summary.hearingAnalystFirstName} ${summary.hearingAnalystMiddleName}"/>
		</c:if>
	</td>
	<td>
		<c:out value="${summary.decisionName}"/>
	</td>
</tr>
</c:forEach>
</fmt:bundle>