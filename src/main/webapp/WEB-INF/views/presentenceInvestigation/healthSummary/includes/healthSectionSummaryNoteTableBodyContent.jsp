<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.healthSectionSummary">
	<c:forEach var="healthSectionSummaryNoteItem" items="${healthSectionSummaryNoteItems}" varStatus="status">
		<c:set var="healthSectionSummaryNoteItem" value="${healthSectionSummaryNoteItem}" scope="request"/>
		<c:set var="healthSectionSummaryNoteItemIndex" value="${status.index}" scope="request"/>
		<c:if test="${not empty healthSectionSummaryNoteItem.itemOperation}">
			<jsp:include page="healthSectionSummaryNoteTableRow.jsp"/>
		</c:if>
	</c:forEach>
</fmt:bundle>