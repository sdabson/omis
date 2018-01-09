<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.incident.msgs.incident">
	<c:forEach var="involvedPartyItem" items="${involvedPartyItems}" varStatus="status">
		<c:set var="involvedPartyItem" value="${involvedPartyItem}" scope="request"/>
		<c:set var="involvedPartyItemIndex" value="${status.index}" scope="request"/>
		<c:set var="involvedPartyCategory" value="${involvedPartyItem.category}" scope="request"/>
		<c:if test="${not empty involvedPartyItem.operation}">
			<jsp:include page="involvedPartyItemTableRow.jsp"/>
		</c:if>
	</c:forEach>
</fmt:bundle>