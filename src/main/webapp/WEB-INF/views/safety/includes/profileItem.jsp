<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.caution.msgs.caution" var="cautionBundle"/>
<fmt:setBundle basename="omis.alert.msgs.alert" var="alertBundle"/>
<fmt:setBundle basename="omis.safety.msgs.safety" var="safetyBundle"/>
<div class="offenderProfileItem">
	<ul class="profileItemHeader header">
		<li><h2><fmt:message key="safetyTitle" bundle="${safetyBundle}"/></h2></li>
	</ul>
	<sec:authorize access="hasRole('CAUTION_MODULE') or hasRole('ADMIN')">
		<ul class="profileItemToolbar">
		<sec:authorize access="hasRole('CAUTION_CREATE') or hasRole('ADMIN')">
			<li><a class="createLink" href="${pageContext.request.contextPath}/caution/create.html?offender=${offender.id}"><span class="visibleLinkLabel"><fmt:message key="createCautionLink" bundle="${cautionBundle}"/></span></a></li>
		</sec:authorize>
		<sec:authorize access="hasRole('CAUTION_LIST') or hasRole('ADMIN')">
			<li><a class="listLink" href="${pageContext.request.contextPath}/caution/list.html?offender=${offender.id}"><span class="visibleLinkLabel"><fmt:message key="listCautionsLink" bundle="${cautionBundle}"/></span></a></li>
		</sec:authorize>
		</ul>
		<div class="profileItemPane">
		<c:choose>
			<c:when test="${cautionCount eq 0}">
				<fmt:message key="noCautionCountMsg" bundle="${cautionBundle}"/>
			</c:when>
			<c:when test="${cautionCount eq 1}">
				<fmt:message key="singleCautionCountMsg" bundle="${cautionBundle}">
					<fmt:param>${cautionCount}</fmt:param>
				</fmt:message>
			</c:when>
			<c:otherwise>
				<fmt:message key="multipleCautionCountMsg" bundle="${cautionBundle}">
					<fmt:param>${cautionCount}</fmt:param>
				</fmt:message>
			</c:otherwise>
		</c:choose>
		</div>
		<div class="profileItemPane">
		<sec:authorize access="hasRole('CAUTION_LIST') or hasRole('ADMIN')">
		<c:if test="${cautionCount gt 0}">
		<c:choose>
			<c:when test="${fn:length(cautionsOnDate) gt 0}">
				<fmt:message key="cautionsOnDateLabel" bundle="${cautionBundle}">
					<fmt:param><fmt:formatDate pattern="MM/dd/yyyy" value="${date}"/></fmt:param>
				</fmt:message>
				<table>
					<tbody>
						<c:forEach var="cautionOnDate" items="${cautionsOnDate}">
						<tr>
							<td><fmt:formatDate pattern="MM/dd/yyyy" value="${cautionOnDate.dateRange.startDate}"/></td>
							<td><fmt:formatDate pattern="MM/dd/yyyy" value="${cautionOnDate.dateRange.endDate}"/></td>
							<td><c:out value="${cautionOnDate.source.name}"/></td>
							<td><c:out value="${cautionOnDate.comment}"/></td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:when>
			<c:otherwise>
				<fmt:message key="noCautionsOnDateMsg" bundle="${cautionBundle}">
					<fmt:param><fmt:formatDate pattern="MM/dd/yyyy" value="${date}"/></fmt:param>
				</fmt:message>
			</c:otherwise>
		</c:choose>
		</c:if>
		</sec:authorize>
		</div>
	</sec:authorize>
	<sec:authorize access="hasRole('ALERT_MODULE') or hasRole('ADMIN')">
		<ul class="profileItemToolbar">
		<sec:authorize access="hasRole('ALERT_CREATE') or hasRole('ADMIN')">
			<li><a class="createLink" href="${pageContext.request.contextPath}/alert/create.html?offender=${offender.id}"><span class="visibleLinkLabel"><fmt:message key="createAlertTitle" bundle="${alertBundle}"/></span></a></li>
		</sec:authorize>
		<sec:authorize access="hasRole('ALERT_LIST') or hasRole('ADMIN')">
			<li><a class="listLink" href="${pageContext.request.contextPath}/alert/list.html?offender=${offender.id}"><span class="visibleLinkLabel"><fmt:message key="listAlertsTitle" bundle="${alertBundle}"/></span></a></li>
		</sec:authorize>
		</ul>
		<div class="profileItemPane">
		<c:choose>
			<c:when test="${fn:length(alerts) gt 0}">
				<fmt:message key="alertsOnDateMsg" bundle="${alertBundle}">
					<fmt:param><fmt:formatDate value="${date}" pattern="MM/dd/yyyy"/></fmt:param>
				</fmt:message>
				<table>
					<tbody>
						<c:forEach var="alert" items="${alerts}">
						<tr>
							<td><fmt:formatDate pattern="MM/dd/yyyy" value="${alert.expireDate}"/></td>
							<td><c:out value="${alert.description}"/></td>
							<td><fmt:formatDate pattern="MM/dd/yyyy" value="${alert.resolution.date}"/></td>
							<td><c:out value="${alert.resolution.description}"/></td>
							<td>
							<c:if test="${not empty alert.resolution.resolvedBy}">
								<c:out value="${alert.resolution.resolvedBy.name.lastName}"/>,
								<c:out value="${alert.resolution.resolvedBy.name.firstName}"/>
							</c:if>
							</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:when>
			<c:otherwise>
				<fmt:message key="noAlertsMsg" bundle="${alertBundle}"/>
			</c:otherwise>
		</c:choose>
		</div>
	</sec:authorize>
</div>