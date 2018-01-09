<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.victimSectionSummary">
	<c:forEach var="victimSectionSummaryNoteItem" items="${victimSectionSummaryNoteItems}" varStatus="status">
		<c:set var="victimSectionSummaryNoteItem" value="${victimSectionSummaryNoteItem}" scope="request"/>
		<c:set var="victimSectionSummaryNoteItemIndex" value="${status.index}" scope="request"/>
		<c:if test="${not empty victimSectionSummaryNoteItem.itemOperation}">
			<jsp:include page="victimSectionSummaryNoteTableRow.jsp"/>
		</c:if>
	</c:forEach>
</fmt:bundle>