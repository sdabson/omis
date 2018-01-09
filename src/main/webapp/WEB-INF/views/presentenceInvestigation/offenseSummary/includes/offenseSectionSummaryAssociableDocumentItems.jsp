	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.offenseSectionSummary">
<a id="createOffenseSectionSummaryAssociableDocumentItemLink" class="createLink addLink">
	<span class="visibleLinkLabel">
		<fmt:message key="addAttachmentLink"/>
	</span>
</a>
<span id="offenseSectionSummaryAssociableDocumentItems">
	<c:forEach var="offenseSectionSummaryAssociableDocumentItem" items="${offenseSectionSummaryAssociableDocumentItems}" varStatus="status">
		<c:set var="offenseSectionSummaryAssociableDocumentItem" value="${offenseSectionSummaryAssociableDocumentItem}" scope="request"/>
		<c:set var="offenseSectionSummaryAssociableDocumentItemIndex" value="${status.index}" scope="request"/>
		<c:if test="${not empty offenseSectionSummaryAssociableDocumentItem.itemOperation}">
			<jsp:include page="offenseSectionSummaryAssociableDocumentItemContent.jsp"/>
		</c:if>
	</c:forEach>
</span>
</fmt:bundle>