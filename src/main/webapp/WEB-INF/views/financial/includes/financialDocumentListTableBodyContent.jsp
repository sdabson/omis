<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.financial.msgs.financial">
<c:forEach var="summary" items="${financialDocumentSummaries}">
<tr>
	<td><a class="actionMenuItem rowActionMenuItem" href="${pageContext.request.contextPath}/financial/financialDocumentAssociationActionMenu.html?financialDocumentAssociation=${summary.financialDocumentId}"></a></td>
	<td>
		<c:out value="${summary.documentTitle}"/>
	</td>
	<td>
		<fmt:formatDate value="${summary.documentDate}" pattern="MM/dd/yyyy"/>
	</td>
</tr>
</c:forEach>
</fmt:bundle>
