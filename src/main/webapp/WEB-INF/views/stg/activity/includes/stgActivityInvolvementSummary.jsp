<%--
 - Table body content of security threat group activity involvements.
 -
 - Author: Trevor Isles
 --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.stg.msgs.stg">
<c:forEach var="offenderSummary" items="${offenderSummaries}"
	varStatus="status">
		<c:out value="${offenderSummary.lastName}"/>,
		<c:out value="${offenderSummary.firstName}"/>
		<c:out value="${offenderSummary.offenderNumber}"/>
		<c:out value="${offenderSummary.summary}"/>
</c:forEach>
</fmt:bundle>