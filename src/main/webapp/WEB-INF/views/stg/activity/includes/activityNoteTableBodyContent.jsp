<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.stg.msgs.stg">
	<c:forEach var="activityNoteItems" items="${noteItems}" varStatus="status">
		<c:set var="activityNoteItem" value="${activityNoteItems}" scope="request"/>
		<c:set var="activityNoteItemIndex" value="${status.index}" scope="request"/>
		<c:if test="${not empty activityNoteItem.operation}">
			<jsp:include page="activityNoteItemTableRow.jsp"/>
		</c:if>
	</c:forEach>
</fmt:bundle>