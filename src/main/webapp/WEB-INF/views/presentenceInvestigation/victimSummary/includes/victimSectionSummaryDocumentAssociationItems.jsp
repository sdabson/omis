	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.victimSectionSummary">
<a id="createVictimSectionSummaryDocumentAssociationItemLink" class="createLink addLink">
	<span class="visibleLinkLabel">
		<fmt:message key="addAttachmentLink"/>
	</span>
</a>
<span id="victimSectionSummaryDocumentAssociationItems">
	<c:forEach var="victimSectionSummaryDocumentAssociationItem" items="${victimSectionSummaryDocumentAssociationItems}" varStatus="status">
		<c:set var="victimSectionSummaryDocumentAssociationItem" value="${victimSectionSummaryDocumentAssociationItem}" scope="request"/>
		<c:set var="victimSectionSummaryDocumentAssociationItemIndex" value="${status.index}" scope="request"/>
		<c:if test="${not empty victimSectionSummaryDocumentAssociationItem.itemOperation}">
			<jsp:include page="victimSectionSummaryDocumentAssociationItemContent.jsp"/>
		</c:if>
	</c:forEach>
</span>
</fmt:bundle>