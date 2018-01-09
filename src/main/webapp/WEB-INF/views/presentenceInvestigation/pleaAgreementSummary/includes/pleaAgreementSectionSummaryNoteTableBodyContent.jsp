<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.pleaAgreementSectionSummary">
	<c:forEach var="pleaAgreementSectionSummaryNoteItem" items="${pleaAgreementSectionSummaryNoteItems}" varStatus="status">
		<c:set var="pleaAgreementSectionSummaryNoteItem" value="${pleaAgreementSectionSummaryNoteItem}" scope="request"/>
		<c:set var="pleaAgreementSectionSummaryNoteItemIndex" value="${status.index}" scope="request"/>
		<c:if test="${not empty pleaAgreementSectionSummaryNoteItem.itemOperation}">
			<jsp:include page="pleaAgreementSectionSummaryNoteTableRow.jsp"/>
		</c:if>
	</c:forEach>
</fmt:bundle>