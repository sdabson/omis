<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.violationevent.msgs.violationEvent">
	<c:forEach var="disciplinaryCodeViolationItem" items="${disciplinaryCodeViolationItems}" varStatus="status">
		<c:set var="disciplinaryCodeViolationItem" value="${disciplinaryCodeViolationItem}" scope="request"/>
		<c:set var="disciplinaryCodeViolationItemIndex" value="${status.index}" scope="request"/>
		<c:if test="${not empty disciplinaryCodeViolationItem.itemOperation}">
			<jsp:include page="disciplinaryCodeViolationTableRow.jsp"/>
		</c:if>
	</c:forEach>
</fmt:bundle>