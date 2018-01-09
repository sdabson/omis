<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.healthSectionSummary">
<a id="createHealthSectionSummaryDocumentItemLink" class="createLink addLink">
	<span class="visibleLinkLabel">
		<fmt:message key="addAttachmentLink"/>
	</span>
</a>
<span id="healthSectionSummaryDocumentAssociationItems">
	<c:forEach var="healthSectionSummaryDocumentAssociationItem" items="${healthSectionSummaryDocumentAssociationItems}" varStatus="status">
		<c:set var="healthSectionSummaryDocumentAssociationItem" value="${healthSectionSummaryDocumentAssociationItem}" scope="request"/>
		<c:set var="healthSectionSummaryDocumentAssociationItemIndex" value="${status.index}" scope="request"/>
		<c:if test="${not empty healthSectionSummaryDocumentAssociationItem.itemOperation}">
			<jsp:include page="healthSectionSummaryDocumentAssociationItemContent.jsp"/>
		</c:if>
	</c:forEach>
</span>
</fmt:bundle>