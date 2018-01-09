<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<fmt:bundle basename="omis.specialneed.msgs.specialNeed">
<c:forEach var="specialNeedSummary" items="${specialNeedSummaries}" varStatus="status">
<c:set var="specialNeedSummary" value="${specialNeedSummary}" scope="request"/>
	<c:choose>
		<c:when test="${specialNeedSummary.active}">
			<c:set var="activeRecord" value="activeRecord"/>
		</c:when>
		<c:otherwise>
			<c:set var="activeRecord" value=""/>
		</c:otherwise>
	</c:choose>
	<tr class="${activeRecord}">
		<td>
			<a class="actionMenuItem rowActionMenuLink" id="specialNeedsRowActionMenuLink${status.index}" href="${pageContext.request.contextPath}/specialNeed/specialNeedsRowActionMenu.html?specialNeed=${specialNeedSummary.id}"></a>
		</td>
		<td><c:out value="${specialNeedSummary.classificationName}"/></td>
		<td>
			<c:out value="${specialNeedSummary.categoryName}"/>
		</td>
		<td>
			<c:out value="${specialNeedSummary.sourceName}"/>
		</td>
		<td>
			<fmt:formatDate value="${specialNeedSummary.startDate}" pattern="MM/dd/yyyy"/>
		</td>
		<td>
			<fmt:formatDate value="${specialNeedSummary.endDate}" pattern="MM/dd/yyyy"/>
		</td>
		<td>
			<c:out value="${specialNeedSummary.comment}"/>
		</td>
	</tr>
</c:forEach>
</fmt:bundle>