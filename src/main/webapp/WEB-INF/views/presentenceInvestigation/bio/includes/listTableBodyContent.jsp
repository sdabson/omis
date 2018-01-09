<%--
 - Author: Stephen Abson
 - Version: 0.1.0 (Oct 30, 2013)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="/WEB-INF/tld/omis.tld" prefix="omis" %>
<fmt:bundle basename="omis.caution.msgs.caution">
<c:forEach var="caution" items="${cautions}">
	<c:choose>
		<c:when test="${omis:isDateRangeActive(caution.dateRange, currentDate)}">
			<c:set var="activeClass" value="activeRecord"/>
		</c:when>
		<c:otherwise>
			<c:set var="activeClass" value="inactiveRecord"/>
		</c:otherwise>
	</c:choose>
	<tr class="${activeClass}">
		<td>
			<a class="actionMenuItem" href="${pageContext.request.contextPath}/caution/cautionsActionMenu.html?caution=${caution.id}"></a>
		</td>
		<td>
			<c:out value="${caution.description.name}"/>
		</td>
		<td>
			<c:out value="${caution.source.name}"/>
		</td>
		<td>
			<fmt:formatDate value="${caution.dateRange.startDate}" pattern="MM/dd/yyyy"/>
		</td>
		<td>
			<fmt:formatDate value="${caution.dateRange.endDate}" pattern="MM/dd/yyyy"/>
		</td>
		<td>
			<c:out value="${caution.comment}"/>
		</td>
	</tr>
</c:forEach>
</fmt:bundle>