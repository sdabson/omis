<%--
  - Displays a day count.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle var="commonBundle" basename="omis.msgs.common"/>
<c:if test="${not empty dayCount}">
	<c:choose>
		<c:when test="${dayCount lt 0}"><fmt:message key="dayCountUntilLabel" bundle="${commonBundle}"><fmt:param value="${dayCount * -1}"/></fmt:message></c:when>
		<c:otherwise><c:out value="${dayCount}"/></c:otherwise>
	</c:choose>
</c:if>