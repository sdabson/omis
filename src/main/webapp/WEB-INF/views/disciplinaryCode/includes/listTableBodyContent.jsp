<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="omis.disciplinaryCode.msgs.disciplinaryCode">
<c:forEach var="summary" items="${disciplinaryCodeSummaries}">
<c:choose>
	<c:when test="${summary.active eq true}">
		<c:set var="activeClass" value="activeRecord" />
	</c:when>
	<c:otherwise>
		<c:set var="activeClass" value="inactiveRecord" />
	</c:otherwise>
</c:choose>
<tr class="${activeClass}">
	<td><a class="actionMenuItem rowActionMenuItem" href="${pageContext.request.contextPath}/disciplinaryCode/disciplinaryCodesRowActionMenu.html?supervisoryOrganizationCode=${summary.supervisoryOrganizationCodeId}"></a></td>
	<td>
		<c:out value="${summary.disciplinaryCodeValue}"/>
	</td>
	<td>
		<c:out value="${summary.disciplinaryCodeDescription}"/>
	</td>
	<td>
		<c:out value="${summary.disciplinaryCodeExtendedDescription}"/>
	</td>
	<td>
		<fmt:formatDate value="${summary.supervisoryOrganizationCodeDateRange.startDate}" pattern="MM/dd/yyyy"/>
	</td>
	<td>
		<fmt:formatDate value="${summary.supervisoryOrganizationCodeDateRange.endDate}" pattern="MM/dd/yyyy"/>
	</td>
	
</tr>
</c:forEach>
</fmt:bundle>