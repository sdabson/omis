<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.otherPertinentInformationSectionSummary">
	<c:forEach var="otherPertinentInformationSectionSummaryNoteItem" items="${otherPertinentInformationSectionSummaryNoteItems}" varStatus="status">
		<c:set var="otherPertinentInformationSectionSummaryNoteItem" value="${otherPertinentInformationSectionSummaryNoteItem}" scope="request"/>
		<c:set var="otherPertinentInformationSectionSummaryNoteItemIndex" value="${status.index}" scope="request"/>
		<c:if test="${not empty otherPertinentInformationSectionSummaryNoteItem.itemOperation}">
			<jsp:include page="otherPertinentInformationSectionSummaryNoteTableRow.jsp"/>
		</c:if>
	</c:forEach>
</fmt:bundle>