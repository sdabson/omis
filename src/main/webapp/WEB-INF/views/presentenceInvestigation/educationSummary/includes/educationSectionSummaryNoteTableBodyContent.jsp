<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.educationSectionSummary">
	<c:forEach var="educationSectionSummaryNoteItem" items="${educationSectionSummaryNoteItems}" varStatus="status">
		<c:set var="educationSectionSummaryNoteItem" value="${educationSectionSummaryNoteItem}" scope="request"/>
		<c:set var="educationSectionSummaryNoteItemIndex" value="${status.index}" scope="request"/>
		<c:if test="${not empty educationSectionSummaryNoteItem.itemOperation}">
			<jsp:include page="educationSectionSummaryNoteTableRow.jsp"/>
		</c:if>
	</c:forEach>
</fmt:bundle>