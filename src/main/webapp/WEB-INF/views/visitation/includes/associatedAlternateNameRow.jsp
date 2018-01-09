<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="address" uri="/WEB-INF/tld/address.tld" %>
<fmt:bundle basename="omis.visitation.msgs.visitation">
	<c:forEach items="${alternateNameSummaries}" var="altNameSummary" varStatus="altNameStatus">		
		<tr id="altNameSummary${altNameSummary.id}" class="altNameSummary">
			<td></td>
			<td>
			<fmt:message key="altNameSummaryLabel">
				<c:choose>
					<c:when test="${not empty altNameSummary.middleName}">
						<c:set value="${fn:substring(altNameSummary.middleName, 0, 1)}." var="middleName"/>						
					</c:when>
					<c:otherwise>
						<c:set value="" var="middleName"/>
					</c:otherwise>											
				</c:choose>
				<c:choose>
					<c:when test="${not empty altNameSummary.suffix}">
						<c:set value="${altNameSummary.suffix}" var="suffix"/>
					</c:when>
					<c:otherwise>
						<c:set value="" var="suffix"/>
					</c:otherwise>
				</c:choose>
				<fmt:param value="${altNameSummary.lastName}"/>
				<fmt:param value="${altNameSummary.firstName}"/>
				<fmt:param value="${middleName}"/>
				<fmt:param value="${suffix}"/>
				<fmt:param value="(${altNameSummary.categoryName})"/>		
			</fmt:message>
			</td>
		</tr>		
	</c:forEach>
</fmt:bundle>