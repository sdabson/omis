<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.hearing.msgs.hearing">
	<c:forEach var="infractionItem" items="${infractionItems}" varStatus="status">
		<c:set var="infractionItem" value="${infractionItem}" scope="request"/>
		<c:set var="infractionItemIndex" value="${status.index}" scope="request"/>
		<jsp:include page="infractionItemTableRow.jsp"/>
	</c:forEach>
</fmt:bundle>