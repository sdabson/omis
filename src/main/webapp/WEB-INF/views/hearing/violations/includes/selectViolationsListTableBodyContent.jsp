<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.hearing.msgs.hearing">
<c:forEach var="summary" items="${violationSummaries}" varStatus="i">
	<c:choose>
		<c:when test="${violationCategory eq 'DISCIPLINARY'}">
			<c:set var="violationId" value="${summary.disciplinaryCodeViolationId}" />
		</c:when>
		<c:when test="${violationCategory eq 'SUPERVISION'}">
			<c:set var="violationId" value="${summary.conditionViolationId}" />
		</c:when>
	</c:choose>
	<c:forEach var="item" items="${violationSelectionItems}">
	<c:if test="${item.disciplinaryCodeViolation.id eq violationId or item.conditionViolation.id eq violationId}">
	<tr class="violationItemRow">
		<td>
		<c:choose>
			<c:when test="${violationCategory eq 'DISCIPLINARY'}">
				<form:input type="hidden" path="violationSelectionItems[${i.index}].disciplinaryCodeViolation"/>
			</c:when>
			<c:when test="${violationCategory eq 'SUPERVISION'}">
				<form:input type="hidden" path="violationSelectionItems[${i.index}].conditionViolation"/>
			</c:when>
		</c:choose>
			<form:checkbox path="violationSelectionItems[${i.index}].selected" class="selectViolationCheckBox"/>
		</td>
		<td>
			<fmt:message key="${summary.violationEventCategory}CategoryLabel"/>
		</td>
		<td>
			<fmt:formatDate value="${summary.violationEventDate}" pattern="MM/dd/yyyy" />
		</td>
		<td>
			<span class="violationDescriptionNoOverflow">
				<c:out value="${summary.violationEventDetails}" />
				<span class="hideOverflow"></span>
			</span>
			<span class="showOverflow"></span>
		</td>
		<td>
			<fmt:message key="violationFormattedLabel">
				<c:choose>
					<c:when test="${violationCategory eq 'DISCIPLINARY'}">
						<fmt:param value="${summary.disciplinaryCodeValue}" />
						<fmt:param value="${summary.disciplinaryCodeDescription}" />
					</c:when>
					<c:when test="${violationCategory eq 'SUPERVISION'}">
						<fmt:param value="${summary.conditionTitle}" />
						<fmt:param value="${summary.conditionClause}" />
					</c:when>
				</c:choose>
			</fmt:message>
		</td>
	</tr>
	</c:if>
	</c:forEach>
</c:forEach>
</fmt:bundle>	