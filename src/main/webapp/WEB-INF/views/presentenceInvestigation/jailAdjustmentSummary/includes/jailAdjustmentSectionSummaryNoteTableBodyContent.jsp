<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.jailAdjustmentSectionSummary">
	<c:forEach var="jailAdjustmentSectionSummaryNoteItem" items="${jailAdjustmentSectionSummaryNoteItems}" varStatus="status">
		<c:set var="jailAdjustmentSectionSummaryNoteItem" value="${jailAdjustmentSectionSummaryNoteItem}" scope="request"/>
		<c:set var="jailAdjustmentSectionSummaryNoteItemIndex" value="${status.index}" scope="request"/>
		<c:if test="${not empty jailAdjustmentSectionSummaryNoteItem.itemOperation}">
			<jsp:include page="jailAdjustmentSectionSummaryNoteTableRow.jsp"/>
		</c:if>
	</c:forEach>
</fmt:bundle>