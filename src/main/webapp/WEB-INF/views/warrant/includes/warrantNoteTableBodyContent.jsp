<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.warrant.msgs.warrant">
	<c:forEach var="warrantNoteItem" items="${warrantNoteItems}" varStatus="status">
		<c:set var="warrantNoteItem" value="${warrantNoteItem}" scope="request"/>
		<c:set var="warrantNoteItemIndex" value="${status.index}" scope="request"/>
		<c:if test="${not empty warrantNoteItem.itemOperation}">
			<jsp:include page="warrantNoteTableRow.jsp"/>
		</c:if>
	</c:forEach>
</fmt:bundle>