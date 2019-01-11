<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.hearing.msgs.hearing">
<c:forEach var="summary" items="${resolvedViolationSummaries}">
<tr class="violationItemRow">
	<td>
		<a class="actionMenuItem rowActionMenuItem" href="${pageContext.request.contextPath}/hearing/violations/resolvedViolationsRowActionMenu.html?infraction=${summary.infractionId}"></a>
	</td>
	<td>
		<fmt:message key="${summary.violationEventCategory}CategoryLabel"/>
	</td>
	<td>
		<fmt:formatDate value="${summary.violationEventDate}" pattern="MM/dd/yyyy" />
	</td>
	<td>
		<c:choose>
			<c:when test="${not empty summary.disciplinaryCodeDescription}">
				<c:choose>
					<c:when test="${not empty summary.modifiedDisciplinaryCode}">
						<fmt:message key="modifiedViolationDetailsLabel">
							<fmt:param value="${summary.modifiedDisciplinaryCode}"/>
							<fmt:param value="${summary.disciplinaryCodeDescription}"/>
						</fmt:message>
					</c:when>
					<c:otherwise>
						<c:out value="${summary.disciplinaryCodeDescription}"/>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:when test="${not empty summary.conditionClause}">
				<c:choose>
					<c:when test="${not empty summary.modifiedConditionClause}">
						<fmt:message key="modifiedViolationDetailsLabel">
							<fmt:param value="${summary.modifiedConditionClause}"/>
							<fmt:param value="${summary.conditionTitle}"/>
						</fmt:message>
					</c:when>
					<c:otherwise>
						<c:out value="${summary.conditionTitle}"/>
					</c:otherwise>
				</c:choose>
			</c:when>
		</c:choose>
	</td>
	<td>
		<c:out value="${summary.decision}${not empty summary.decision and not empty summary.decisionReason ? ' - ' : ''}${summary.decisionReason}"/>
	</td>
	<td>
		<c:if test="${not empty summary.dispositionCategory}">
			<fmt:message key="${summary.dispositionCategory}DispositionLabel"/>
		</c:if>
	</td>
	<td>
		<fmt:message key="${summary.resolutionCategory}ResolutionLabel"/>
	</td>
	<td>
		<fmt:formatDate value="${summary.appealDate}" pattern="MM/dd/yyyy" />
	</td>
	<td>
		<c:out value="${summary.sanctionDescription}"/>
	</td>
</tr>
</c:forEach>
</fmt:bundle>	