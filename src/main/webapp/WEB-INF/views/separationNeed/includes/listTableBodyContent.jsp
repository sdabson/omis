<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<fmt:bundle basename="omis.separationneed.msgs.separationNeed">
	<c:forEach var="summary" items="${separationNeedSummaries}">
		<c:choose>
			<c:when test="${offender.id eq summary.targetOffenderId}">
				<c:set value="${summary.subjectOffenderId}" var="summaryOffenderId"/>
				<c:set value="${summary.subjectOffenderNumber}" var="summaryOffenderNumber"/>
				<c:set value="${summary.subjectOffenderFirstName}" var="summaryOffenderFirstName"/>
				<c:set value="${summary.subjectOffenderLastName}" var="summaryOffenderLastName"/>
				<c:set value="${summary.subjectOffenderMiddleInitial}" var="summaryOffenderMiddleInitial"/>
				<c:set value="${summary.subjectOffenderHousingLocation}" var="summaryOffenderHousingLocation"/>
			</c:when>
			<c:otherwise>
				<c:set value="${summary.targetOffenderNumber}" var="summaryOffenderId"/>
				<c:set value="${summary.targetOffenderNumber}" var="summaryOffenderNumber"/>
				<c:set value="${summary.targetOffenderFirstName}" var="summaryOffenderFirstName"/>
				<c:set value="${summary.targetOffenderLastName}" var="summaryOffenderLastName"/>
				<c:set value="${summary.targetOffenderMiddleInitial}" var="summaryOffenderMiddleInitial"/>
				<c:set value="${summary.targetOffenderHousingLocation}" var="summaryOffenderHousingLocation"/>
			</c:otherwise>
		</c:choose>
		<c:set value="" var="rowClassName"/>
			<c:choose>
				<c:when test="${summary.active}">
					<c:set var="rowClassName" value="${rowClassName} active"/>
				</c:when>
				<c:otherwise>
					<c:set var="rowClassName" value="${rowClassName} inactive"/>
				</c:otherwise>
			</c:choose>
			<c:if test="${not empty summary.removalDate}">
				<c:set var="rowClassName" value="${rowClassName} removed"/>
			</c:if>
		<tr class="separationNeedRow ${rowClassName}">
			<td>
				<a class="actionMenuItem" id="separationNeedRowActionMenuLink${status.index}" href="${pageContext.request.contextPath}/separationNeed/separationNeedsRowActionMenu.html?separationNeed=${summary.separationNeedId}&&offender=${offender.id}"></a>
			</td>
			<td>
				<fmt:formatNumber value="${summaryOffenderNumber}" pattern="#######" var="summaryOffenderNumber" />
				<fmt:message key="offenderLastFirstName">
						<fmt:param value="${summaryOffenderFirstName}"/>
						<fmt:param value="${summaryOffenderLastName}"/>
				</fmt:message>
				<c:if test="${not empty summaryOffenderMiddleInitial}">
					<fmt:message key="offenderMiddleInitial">
						<fmt:param value="${summaryOffenderMiddleInitial}"/>
					</fmt:message>
				</c:if>
				<fmt:message key="offenderNumber">
					<fmt:param value="${summaryOffenderNumber}"/>
				</fmt:message>
			</td>
			<td>
				<c:choose>
					<c:when test="${summary.active}">
						<fmt:message key="activeSeparationNeedLabel"/>
					</c:when>
					<c:otherwise>
						<fmt:message key="inactiveSeparationNeedLabel"/>
					</c:otherwise>
				</c:choose>
			</td>
			<td>
				<c:out value="${summaryOffenderHousingLocation}"/>
			</td>
			<td>
			<c:if test="${not empty summary.creationComment}">
				<a class="commentLink" title="<c:out value="${summary.creationComment}"/>"></a>
			</c:if>
			</td>
			<td>
				<fmt:formatDate pattern="MM/dd/yyyy" value="${summary.date}"/>
			</td>
			<td>
				<fmt:formatDate pattern="MM/dd/yyyy" value="${summary.removalDate}"/>
			</td>
		</tr>
	</c:forEach>
</fmt:bundle>
