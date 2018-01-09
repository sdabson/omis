<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="/WEB-INF/tld/omis.tld" prefix="omis" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<c:forEach var="alternativeNameAssociation" items="${alternativeNameAssociations}" varStatus="status">
	<c:choose>
		<c:when test="${omis:isDateRangeActive(alternativeNameAssociation.dateRange, currentDate)}">
			<c:set var="activeClass" value="activeRecord"/>
		</c:when>
		<c:otherwise>
			<c:set var="activeClass" value="inactiveRecord"/>
		</c:otherwise>
	</c:choose>
	<tr class="${activeClass} alternativeNameRow">
		<td>
			<a class="actionMenuItem" id="alternativeNameAssociationsRowActionMenuLink${status.index}" href="${pageContext.request.contextPath}/offender/name/alternative/alternativeNameAssociationsRowActionMenu.html?alternativeNameAssociation=${alternativeNameAssociation.id}"></a>
		</td>
		<td><c:out value="${alternativeNameAssociation.name.lastName}"/></td>
		<td><c:out value="${alternativeNameAssociation.name.firstName}"/></td>
		<td><c:out value="${alternativeNameAssociation.name.middleName}"/></td>
		<td><c:out value="${alternativeNameAssociation.name.suffix}"/></td>
		<td><c:out value="${alternativeNameAssociation.category.name}"/></td>
		<td><fmt:formatDate value="${alternativeNameAssociation.dateRange.startDate}" pattern="MM/dd/yyyy"/></td>
		<td><fmt:formatDate value="${alternativeNameAssociation.dateRange.endDate}" pattern="MM/dd/yyyy"/></td>
	</tr>
</c:forEach>