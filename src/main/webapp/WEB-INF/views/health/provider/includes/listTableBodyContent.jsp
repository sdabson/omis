<%--
 - Author: Ryan Johns
 - Version: 0.1.0 (Apr 5, 2014)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.health.msgs.health">
<fmt:message key="editLabel" var="editLabel"  bundle="${commonBundle}"/>
<fmt:message key="editScheduleLabel" var="editScheduleLabel"/>

<c:forEach var="provider" items="${providerList}">
	<tr>
		<td>
		<a class="viewEditLink" title="${editLabel}" href="${pageContext.request.contextPath}/health/provider/edit.html?assignment=${provider.id}">
		</a> 
		<a class="calendarLink" title="${editScheduleLabel}" href="${pageContext.request.contextPath}/health/provider/schedule/internal/edit.html?providerAssignment=${provider.id}"></a>
		</td>
		<td>
			<c:out value="${provider.lastName}"/>, <c:out value="${provider.firstName}"/> <c:out value="${provider.middleName}"/>
		</td><td>
			<c:out value="${provider.providerAssignmentTitleName}"/>
		</td><td>
			<c:out value="${provider.providerAssignmentTitleAbbreviation}"/>
		</td><td>
			<fmt:message key="providerAssignmentCategoryLabel.${provider.providerAssignmentCategory.name}"/>
		</td><td>
			<fmt:formatDate value="${provider.startDate}" pattern="MM/dd/yyyy"/>
		</td><td>
			<fmt:formatDate value="${provider.endDate}" pattern="MM/dd/yyyy"/>
		</td><td>
			<c:choose>
				<c:when test="${not empty provider.contracted && provider.contracted}">
					<fmt:message key="contractedLabel"/>
				</c:when>
			<c:when test="${not empty provider.medicalFacilityName}">
				<fmt:message key="externalLabel">
					<fmt:param value="${provider.medicalFacilityName}"/>
				</fmt:message>
			</c:when>
			<c:when test="${empty provider.medicalFacilityName && (empty provider.contracted || !provider.contracted)}">
				<fmt:message key="internalLabel"/>
			</c:when>
			</c:choose>
		</td>
	</tr>
</c:forEach>
</fmt:bundle>