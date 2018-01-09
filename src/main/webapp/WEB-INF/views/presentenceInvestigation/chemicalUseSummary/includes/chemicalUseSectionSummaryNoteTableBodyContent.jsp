<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.chemicalUseSectionSummary">
	<c:forEach var="chemicalUseSectionSummaryNoteItem" items="${chemicalUseSectionSummaryNoteItems}" varStatus="status">
		<c:set var="chemicalUseSectionSummaryNoteItem" value="${chemicalUseSectionSummaryNoteItem}" scope="request"/>
		<c:set var="chemicalUseSectionSummaryNoteItemIndex" value="${status.index}" scope="request"/>
		<c:if test="${not empty chemicalUseSectionSummaryNoteItem.itemOperation}">
			<jsp:include page="chemicalUseSectionSummaryNoteTableRow.jsp"/>
		</c:if>
	</c:forEach>
</fmt:bundle>