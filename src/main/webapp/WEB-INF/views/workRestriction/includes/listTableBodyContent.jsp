<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="omis.workrestriction.msgs.workRestriction">
<c:forEach var="summary" items="${summaries}">
<tr>
	<td><a class="actionMenuItem rowActionMenuItem" href="${pageContext.request.contextPath}/workRestriction/workRestrictionsRowActionMenu.html?workRestriction=${summary.id}"></a></td>
	<td>
		<c:out value="${summary.categoryName}"/>
	</td>
	<td>
		<c:if test="${not empty summary.authorizedByUserName}">
			<c:out value="${summary.authorizedByLastName}, ${summary.authorizedByFirstName} (${summary.authorizedByUserName})"/>
		</c:if>
	</td>
	<td>
		<fmt:formatDate value="${summary.authorizationDate}" pattern="MM/dd/yyyy"/>
	</td>
	<td>
		<c:out value="${summary.notes}" />
	</td>
</tr>
</c:forEach>
</fmt:bundle>