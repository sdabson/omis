<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<jsp:include page="../../includes/nullOption.jsp"/>
<c:forEach items="${boardMeetingSites}" var="boardMeetingSite">
	<c:set var="siteDate">
		<fmt:formatDate value="${boardMeetingSite.date}" pattern="MM/dd/yyyy" />
	</c:set>
	<option value="${boardMeetingSite.id}">
		<c:out value="${boardMeetingSite.location.organization.name} - ${siteDate}"/>
	</option>
</c:forEach>