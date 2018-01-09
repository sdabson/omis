<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.education.msgs.education">
<c:forEach var="summary" items="${educationDocumentSummaries}">
<tr>
	<td><a class="actionMenuItem rowActionMenuItem" href="${pageContext.request.contextPath}/education/educationDocumentsRowActionMenu.html?educationAssociableDocument=${summary.educationDocumentId}"></a></td>
	<td>
		<c:out value="${summary.documentTitle}" />
	</td>
	<td>
		<c:out value="${summary.categoryName}" />
	</td>
	<td>
		<fmt:formatDate value="${summary.documentDate}" pattern="MM/dd/yyyy"/>
	</td>
</tr>
</c:forEach>
</fmt:bundle>