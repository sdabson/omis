<%--
 - List of items of location reason terms summaries.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:forEach var="locationReasonTermSummary" items="${locationReasonTermSummaries}">
	<c:set var="locationReasonTermSummary" value="${locationReasonTermSummary}" scope="request"/>
	<jsp:include page="locationReasonTermSummaryItem.jsp"/>
</c:forEach>