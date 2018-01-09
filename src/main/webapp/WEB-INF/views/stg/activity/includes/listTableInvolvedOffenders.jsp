<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.stg.msgs.stg">
	<c:forEach var="involvementItem" items="${involvementItems}" varStatus="status">
		<c:set var="involvementItem" value="${involvementItem}" scope="request"/>
		<c:set var="activityInvolvementItemIndex" value="${status.index}" scope="request"/>
		<c:if test="${not empty involvementItem.operation}">
			<jsp:include page="activityInvolvementItemTableRow.jsp"/>
		</c:if>
	</c:forEach>
</fmt:bundle>