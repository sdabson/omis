<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:bundle basename="omis.probationparole.msgs.probationParoleDocument">
<c:forEach var="summary" items="${probationParoleDocumentAssociationSummaries}">
	<tr>
		<td><a class="actionMenuItem rowActionMenuItem" href="${pageContext.request.contextPath}/probationParole/document/probationParoleDocumentsRowActionMenu.html?probationParoleDocumentAssociation=${summary.probationParoleDocumentAssociationId}"></a></td>
		<td>
			<fmt:formatDate value="${summary.fileDate}" pattern="MM/dd/yyyy"/>
		</td>
		<td>
			<c:out value="${summary.categoryName}"/>
		</td>
		<td>
			<c:out value="${summary.documentTitle}"/>
		</td>
		<td>
			<fmt:message key="updatedByUserLabel">
				<fmt:param value="${summary.updateUserLastName}" />
				<fmt:param value="${summary.updateUserFirstName}" />
			</fmt:message>
		</td>
	</tr>
</c:forEach>
</fmt:bundle>