<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="/WEB-INF/tld/omis.tld" prefix="omis"%>
<fmt:bundle basename="omis.courtcasecondition.msgs.courtCaseConditions">
	<td class="operations"></td>
	<td colspan="3">
		<table id="conditions" class="listTable">
			<thead>
				<tr>
					<th class="operations" />
					<th><fmt:message key="conditionTitleLabel" /></th>
					<th><fmt:message key="conditionClauseLabel" /></th>
				</tr>
			</thead>
			<tbody>
				<tr class="listTableRowNoHighlight">
					<td><a class="actionMenuItem rowActionMenuItem${courtCaseAgreement.id}" id="rowActionMenuItem${courtCaseAgreement.id}_standard"
						href="${pageContext.request.contextPath}/courtCaseCondition/condition/courtCaseConditionsStandardRowActionMenu.html?courtCaseAgreement=${courtCaseAgreement.id}"></a>
					</td>
					<td colspan="2">
						<h3><fmt:message key="standardConditionsLabel" /></h3>
					</td>
				</tr>
				<c:choose>
					<c:when test="${not empty standardConditions}">
						<c:forEach var="conditionSummary" items="${standardConditions}">
							<c:if test="${not conditionSummary.waived}">
								<tr>
									<td />
									<td><c:out value="${conditionSummary.conditionTitle}" /></td>
									<td><c:out value="${conditionSummary.conditionClause}" /></td>
								</tr>
							</c:if>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr class="listTableRowNoHighlight">
							<td colspan="3">
								<fmt:message key="noConditions" />
							</td>
						</tr>
					</c:otherwise>
				</c:choose>
				<c:forEach var="specialConditionsGroup" items="${specialConditions}">
					<tr class="listTableRowNoHighlight">
						<td><a class="actionMenuItem rowActionMenuItem${courtCaseAgreement.id}" id="rowActionMenuItem${courtCaseAgreement.id}_${specialConditionsGroup.key.id}"
						href="${pageContext.request.contextPath}/courtCaseCondition/condition/courtCaseConditionsGroupRowActionMenu.html?courtCaseAgreement=${courtCaseAgreement.id}&conditionGroup=${specialConditionsGroup.key.id}"></a>
						</td>
						<td colspan="2">
							<h3><c:out value="${specialConditionsGroup.key.name}"/></h3>
						</td>
					</tr>
					<c:forEach var="conditionSummary" items="${specialConditionsGroup.value}">
						<tr>
							<td />
							<td><c:out value="${conditionSummary.conditionTitle}" /></td>
							<td><c:out value="${conditionSummary.conditionClause}" /></td>
						</tr>
					</c:forEach>
				</c:forEach>
			</tbody>
		</table>
	</td>
</fmt:bundle>