<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.hearing.msgs.hearing">
<c:forEach var="summary" items="${hearingSummaries}">
<tr>
	<td><a class="actionMenuItem rowActionMenuItem" href="${pageContext.request.contextPath}/hearing/hearingsRowActionMenu.html?hearing=${summary.hearingId}"></a></td>
	<td>
		<fmt:formatDate value="${summary.hearingDate}" pattern="MM/dd/yyyy" />
	</td>
	<td>
		<c:out value="${summary.locationName}"/>
	</td>
	<td>
		<fmt:message key="${summary.category}CategoryLabel"/>
	</td>
	<td>
		<fmt:message key="${summary.hearingStatusCategory}StatusLabel"/>
	</td>
</tr>
</c:forEach>
</fmt:bundle>	