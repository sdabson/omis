<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.psychologicalSectionSummary">
	<c:forEach var="psychologicalSectionSummaryNoteItem" items="${psychologicalSectionSummaryNoteItems}" varStatus="status">
		<c:set var="psychologicalSectionSummaryNoteItem" value="${psychologicalSectionSummaryNoteItem}" scope="request"/>
		<c:set var="psychologicalSectionSummaryNoteItemIndex" value="${status.index}" scope="request"/>
		<c:if test="${not empty psychologicalSectionSummaryNoteItem.itemOperation}">
			<jsp:include page="psychologicalSectionSummaryNoteTableRow.jsp"/>
		</c:if>
	</c:forEach>
</fmt:bundle>