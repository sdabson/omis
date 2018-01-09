<%--
 - Displays content of weekly schedule for provider table.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<fmt:setBundle basename="omis.health.msgs.health" var="healthBundle"/>
		<c:choose>
			<c:when test="${fn:length(internalProviderScheduleDayItems) gt 0}">
			<c:forEach var="internalProviderScheduleDayItem" items="${internalProviderScheduleDayItems}" varStatus="status">
				<c:set var="internalProviderScheduleDayItem" value="${internalProviderScheduleDayItem}" scope="request"/>
				<jsp:include page="dailyTableRow.jsp"/>
			</c:forEach>
			</c:when>
			<c:otherwise>
				<tr class="messageRow">
				 <td colspan="4" class="message"><fmt:message key="selectSearchProviderScheduleCriteriaMessage" bundle="${healthBundle}"/></td>
				</tr>
			</c:otherwise>
		</c:choose>