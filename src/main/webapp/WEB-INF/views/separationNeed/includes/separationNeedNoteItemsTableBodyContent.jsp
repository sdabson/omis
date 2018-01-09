<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.separationneed.msgs.separationNeed">
	<c:forEach var="separationNeedNoteItem" items="${separationNeedNoteItems}" varStatus="status">
		<c:set var="separationNeedNoteItem" value="${separationNeedNoteItem}" scope="request"/>
		<c:set var="separationNeedNoteItemIndex" value="${status.index}" scope="request"/>
		<c:if test="${not empty separationNeedNoteItem.operation}">
			<jsp:include page="separationNeedNoteItemTableRow.jsp"/>
		</c:if>
	</c:forEach>
</fmt:bundle>