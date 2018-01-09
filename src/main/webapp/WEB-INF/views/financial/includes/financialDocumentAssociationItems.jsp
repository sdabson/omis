<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.financial.msgs.financial">
<a id="createFinancialDocumentAssociationItemLink" class="createLink addLink">
	<span class="visibleLinkLabel">
		<fmt:message key="addAttachmentLink"/>
	</span>
</a>
<span id="financialDocumentAssociationItems">
	<c:forEach var="financialDocumentAssociationItem" items="${financialDocumentAssociationItems}" varStatus="status">
		<c:set var="financialDocumentAssociationItem" value="${financialDocumentAssociationItem}" scope="request"/>
		<c:set var="financialDocumentAssociationItemIndex" value="${status.index}" scope="request"/>
		<c:if test="${not empty financialDocumentAssociationItem.operation}">
			<jsp:include page="/WEB-INF/views/financial/financialDocumentAssociation/includes/editFinancialDocumentAssociationTableRow.jsp"/>
		</c:if>
	</c:forEach>
</span>
</fmt:bundle>