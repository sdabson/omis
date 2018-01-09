<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.presentenceInvestigationHome">
	<c:forEach var="presentenceInvestigationRequestNoteItem" items="${presentenceInvestigationRequestNoteItems}" varStatus="status">
		<c:set var="presentenceInvestigationRequestNoteItem" value="${presentenceInvestigationRequestNoteItem}" scope="request"/>
		<c:set var="presentenceInvestigationRequestNoteItemIndex" value="${status.index}" scope="request"/>
		<c:if test="${not empty presentenceInvestigationRequestNoteItem.itemOperation}">
			<jsp:include page="presentenceInvestigationRequestNoteTableRow.jsp"/>
		</c:if>
	</c:forEach>
</fmt:bundle>