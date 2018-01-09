<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="omis.disciplinaryCode.msgs.disciplinaryCode">
<c:forEach var="summary" items="${supervisoryOrganizationSummaries}">
<tr>
	<td><a class="actionMenuItem rowActionMenuItem" href="${pageContext.request.contextPath}/disciplinaryCode/organizationsRowActionMenu.html?supervisoryOrganization=${summary.organizationId}"></a></td>
	<td>
		<c:out value="${summary.name}"/>
	</td>
</tr>
</c:forEach>
</fmt:bundle>