<%--
 - Author: Jonny Santy
 - Author: Annie Jacques
 - Version: 0.1.1 (Aug 30, 2017)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="/WEB-INF/tld/omis.tld" prefix="omis"%>
<fmt:bundle basename="omis.courtcasecondition.msgs.courtCaseConditions">
	<c:forEach var="courtCaseAgreementSummary" items="${courtCaseAgreementSummaries}" varStatus="status">
		<c:choose>
			<c:when test="${not empty courtCaseAgreementSummary.conditionSummaries}">
				<c:forEach var="conditionSummary" items="${courtCaseAgreementSummary.conditionSummaries}">
					<c:set var="activeClass" value="${conditionSummary.active ? 'activeRecord' : 'inactiveRecord'}" />
				</c:forEach>
			</c:when>
		</c:choose>
		<tr class="${activeClass}">
			<td><a class="actionMenuItem rowActionMenuItem" id="rowActionMenuItem${status.index}"
				href="${pageContext.request.contextPath}/courtCaseCondition/condition/courtCaseConditionsRowActionMenu.html?courtCaseAgreement=${courtCaseAgreementSummary.courtCaseAgreementId}"></a>
			</td>
			<td><c:out value="${courtCaseAgreementSummary.docketValue}" /></td>
			<td><c:out value="${courtCaseAgreementSummary.agreementCategoryName}" /></td> 
				<td><fmt:formatDate 
						value="${courtCaseAgreementSummary.startDate}" 
						pattern="MM/dd/yyyy" /> - <fmt:formatDate 
						value="${courtCaseAgreementSummary.endDate}" pattern="MM/dd/yyyy" /> 
			</td>
		</tr>
		<tr id="conditions${courtCaseAgreementSummary.courtCaseAgreementId}" class="listTableRowNoHighlight" />
	</c:forEach>
</fmt:bundle>



