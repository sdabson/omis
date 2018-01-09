<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.detainernotification.msgs.detainerNotification">
	<c:forEach var="detainerNoteItem" items="${noteItems}" varStatus="status">
		<c:set var="detainerNoteItem" value="${detainerNoteItem}" scope="request"/>
		<c:set var="detainerNoteItemIndex" value="${status.index}" scope="request"/>
		<c:if test="${not empty detainerNoteItem.operation}">
			<jsp:include page="detainerNoteItemTableRow.jsp"/>
		</c:if>
	</c:forEach>
</fmt:bundle>