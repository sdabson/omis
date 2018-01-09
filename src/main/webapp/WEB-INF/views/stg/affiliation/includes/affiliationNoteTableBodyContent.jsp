<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.stg.msgs.stg">
	<c:forEach var="affiliationNoteItem" items="${affiliationNoteItems}" varStatus="status">
		<c:set var="affiliationNoteItem" value="${affiliationNoteItem}" scope="request"/>
		<c:set var="affiliationNoteItemIndex" value="${status.index}" scope="request"/>
		<c:if test="${not empty affiliationNoteItem.operation}">
			<jsp:include page="affiliationNoteItemTableRow.jsp"/>
		</c:if>
	</c:forEach>
</fmt:bundle>