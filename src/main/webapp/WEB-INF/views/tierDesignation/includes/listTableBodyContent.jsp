<%--
 - Author: Jason Nelson
 - Author: Stephen Abson
 --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tld/omis.tld" prefix="omis" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.tierdesignation.msgs.tierDesignation">
<c:forEach var="tierDesignation" items="${tierDesignations}">
	<c:choose>
		<c:when test="${omis:isDateRangeActive(tierDesignation.dateRange, currentDate)}">
			<c:set var="activeClass" value="activeRecord"/>
		</c:when>
		<c:otherwise>
			<c:set var="activeClass" value="inactiveRecord"/>
		</c:otherwise>
	</c:choose>
	<tr class="${activeClass}">
		<td>
			<a class="actionMenuItem" href="${pageContext.request.contextPath}/tierDesignation/tierDesignationsActionMenu.html?tierDesignation=${tierDesignation.id}"></a>
		</td>
		<td><c:out value="${tierDesignation.level.name}"/></td>
		<td><c:out value="${tierDesignation.source.name}"/></td>
		<td><c:out value="${tierDesignation.changeReason.name}"/></td>			
		<td><fmt:formatDate value="${tierDesignation.dateRange.startDate}" pattern="MM/dd/yyyy"/></td>
		<td><fmt:formatDate value="${tierDesignation.dateRange.endDate}" pattern="MM/dd/yyyy"/></td>	
	</tr>
</c:forEach>
</fmt:bundle>