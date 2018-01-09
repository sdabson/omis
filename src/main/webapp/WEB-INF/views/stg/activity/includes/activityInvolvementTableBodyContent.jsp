<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.stg.msgs.stg">
	<c:forEach var="activityInvolvementItems" items="${involvementItems}" varStatus="status">
		<c:set var="activityInvolvementItem" value="${activityInvolvementItems}" scope="request"/>
		<c:set var="activityInvolvementItemIndex" value="${status.index}" scope="request"/>
		<c:if test="${not empty activityInvolvementItem.operation}">
			<jsp:include page="activityInvolvementItemTableRow.jsp"/>
		</c:if>
	</c:forEach>
</fmt:bundle>