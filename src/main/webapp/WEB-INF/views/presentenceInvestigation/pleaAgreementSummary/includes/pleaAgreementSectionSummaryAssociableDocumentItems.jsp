	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.pleaAgreementSectionSummary">
<a id="createPleaAgreementSectionSummaryAssociableDocumentItemLink" class="createLink addLink">
	<span class="visibleLinkLabel">
		<fmt:message key="addAttachmentLink"/>
	</span>
</a>
<span id="pleaAgreementSectionSummaryAssociableDocumentItems">
	<c:forEach var="pleaAgreementSectionSummaryAssociableDocumentItem" items="${pleaAgreementSectionSummaryAssociableDocumentItems}" varStatus="status">
		<c:set var="pleaAgreementSectionSummaryAssociableDocumentItem" value="${pleaAgreementSectionSummaryAssociableDocumentItem}" scope="request"/>
		<c:set var="pleaAgreementSectionSummaryAssociableDocumentItemIndex" value="${status.index}" scope="request"/>
		<c:if test="${not empty pleaAgreementSectionSummaryAssociableDocumentItem.itemOperation}">
			<jsp:include page="pleaAgreementSectionSummaryAssociableDocumentItemContent.jsp"/>
		</c:if>
	</c:forEach>
</span>
</fmt:bundle>