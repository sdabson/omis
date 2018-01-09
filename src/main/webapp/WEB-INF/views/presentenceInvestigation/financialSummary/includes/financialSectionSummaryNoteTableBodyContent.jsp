<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.financialSummary">
	<c:forEach var="financialSectionSummaryNoteItem" items="${financialSectionSummaryNoteItems}" varStatus="status">
		<c:set var="financialSectionSummaryNoteItem" value="${financialSectionSummaryNoteItem}" scope="request"/>
		<c:set var="financialSectionSummaryNoteItemIndex" value="${status.index}" scope="request"/>
		<c:if test="${not empty financialSectionSummaryNoteItem.itemOperation}">
			<jsp:include page="financialSectionSummaryNoteTableRow.jsp"/>
		</c:if>
	</c:forEach>
</fmt:bundle>