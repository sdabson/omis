	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.chemicalUseSectionSummary">
<a id="createChemicalUseSectionSummaryDocumentAssociationItemLink" class="createLink addLink">
	<span class="visibleLinkLabel">
		<fmt:message key="addAttachmentLink"/>
	</span>
</a>
<span id="chemicalUseSectionSummaryDocumentAssociationItems">
	<c:forEach var="chemicalUseSectionSummaryDocumentAssociationItem" items="${chemicalUseSectionSummaryDocumentAssociationItems}" varStatus="status">
		<c:set var="chemicalUseSectionSummaryDocumentAssociationItem" value="${chemicalUseSectionSummaryDocumentAssociationItem}" scope="request"/>
		<c:set var="chemicalUseSectionSummaryDocumentAssociationItemIndex" value="${status.index}" scope="request"/>
		<c:if test="${not empty chemicalUseSectionSummaryDocumentAssociationItem.itemOperation}">
			<jsp:include page="chemicalUseSectionSummaryDocumentAssociationItemContent.jsp"/>
		</c:if>
	</c:forEach>
</span>
</fmt:bundle>