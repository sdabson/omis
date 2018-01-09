<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.financialSectionSummary">
<a id="createFinancialSectionSummaryDocumentAssociationItemLink" class="createLink addLink">
	<span class="visibleLinkLabel">
		<fmt:message key="addAttachmentLink"/>
	</span>
</a>
<span id="financialSectionSummaryDocumentAssociationItems">
	<c:forEach var="financialSectionSummaryDocumentAssociationItem" items="${financialSectionSummaryDocumentAssociationItems}" varStatus="status">
		<c:set var="financialSectionSummaryDocumentAssociationItem" value="${financialSectionSummaryDocumentAssociationItem}" scope="request"/>
		<c:set var="financialSectionSummaryDocumentAssociationItemIndex" value="${status.index}" scope="request"/>
		<c:if test="${not empty financialSectionSummaryDocumentAssociationItem.itemOperation}">
			<jsp:include page="financialSectionSummaryDocumentAssociationItemContent.jsp"/>
		</c:if>
	</c:forEach>
</span>
</fmt:bundle>