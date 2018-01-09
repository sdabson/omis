<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.separationneed.msgs.separationNeed">
	<c:forEach var="separationNeedReasonItem" items="${separationNeedReasonItems}" varStatus="status">
		<c:set var="separationNeedReasonItem" value="${separationNeedReasonItem}" scope="request"/>
		<c:set var="separationNeedReasonItemIndex" value="${status.index}" scope="request"/>
		<c:if test="${not empty separationNeedReasonItem.operation}">
			<jsp:include page="separationNeedReasonItemTableRow.jsp"/>
		</c:if>
	</c:forEach>
</fmt:bundle>