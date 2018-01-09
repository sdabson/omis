<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.victim.msgs.education">
<c:forEach var="summary" items="${victimDocumentAssociationSummaries}">
<tr>
	<td><a class="actionMenuItem rowActionMenuItem" href="${pageContext.request.contextPath}/presentenceInvestigation/victimSummary/victimDocumentsRowActionMenu.html?victimAssociableDocument=${summary.id}"></a></td>
	<td>
		<c:out value="${summary.documentTitle}" />
	</td>
	<td>
		<fmt:formatDate value="${summary.documentUploadDate}" pattern="MM/dd/yyyy"/>
	</td>
	<td>
		<c:out value="${summary.userLastName}, ${summary.userFirstName}"/>
	</td>
</tr>
</c:forEach>
</fmt:bundle>