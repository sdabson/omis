<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.education.msgs.education">
	<c:forEach var="achievementItem" items="${achievementItems}" varStatus="status">
		<c:set var="achievementItem" value="${achievementItem}" scope="request"/>
		<c:set var="achievementItemIndex" value="${status.index}" scope="request"/>
		<c:if test="${not empty achievementItem.operation}">
			<jsp:include page="achievementItemTableRow.jsp"/>
		</c:if>
	</c:forEach>
</fmt:bundle>