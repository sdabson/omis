	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.psychologicalSectionSummary">
<a id="createPsychologicalSectionSummaryDocumentItemLink" class="createLink addLink">
	<span class="visibleLinkLabel">
		<fmt:message key="addAttachmentLink"/>
	</span>
</a>
<span id="psychologicalSectionSummaryDocumentItems">
	<c:forEach var="psychologicalSectionSummaryDocumentItem" items="${psychologicalSectionSummaryDocumentItems}" varStatus="status">
		<c:set var="psychologicalSectionSummaryDocumentItem" value="${psychologicalSectionSummaryDocumentItem}" scope="request"/>
		<c:set var="psychologicalSectionSummaryDocumentItemIndex" value="${status.index}" scope="request"/>
		<c:if test="${not empty psychologicalSectionSummaryDocumentItem.itemOperation}">
			<jsp:include page="psychologicalSectionSummaryDocumentItemContent.jsp"/>
		</c:if>
	</c:forEach>
</span>
</fmt:bundle>