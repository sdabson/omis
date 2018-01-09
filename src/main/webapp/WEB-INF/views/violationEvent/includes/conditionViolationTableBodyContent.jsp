<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.violationevent.msgs.violationEvent">
	<c:forEach var="conditionViolationItem" items="${conditionViolationItems}" varStatus="status">
		<c:set var="conditionViolationItem" value="${conditionViolationItem}" scope="request"/>
		<c:set var="conditionViolationItemIndex" value="${status.index}" scope="request"/>
		<c:if test="${not empty conditionViolationItem.itemOperation}">
			<jsp:include page="conditionViolationTableRow.jsp"/>
		</c:if>
	</c:forEach>
</fmt:bundle>