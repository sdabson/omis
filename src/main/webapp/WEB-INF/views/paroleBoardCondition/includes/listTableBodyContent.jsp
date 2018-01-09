<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="/WEB-INF/tld/omis.tld" prefix="omis"%>
<fmt:bundle basename="omis.paroleboardcondition.msgs.paroleBoardCondition">
	<c:forEach var="paroleBoardAgreementSummary" items="${paroleBoardAgreementSummaries}" varStatus="status">
		<c:choose>
			<c:when test="${not empty paroleBoardAgreementSummary.conditionSummaries}">
				<c:forEach var="conditionSummary" items="${paroleBoardAgreementSummary.conditionSummaries}">
					<c:set var="activeClass" value="${conditionSummary.active ? 'activeRecord' : 'inactiveRecord'}" />
				</c:forEach>
			</c:when>
		</c:choose>
		<tr class="${activeClass}">
			<td><a class="actionMenuItem rowActionMenuItem" id="rowActionMenuItem${status.index}"
				href="${pageContext.request.contextPath}/paroleBoardCondition/paroleBoardAgreementsRowActionMenu.html?paroleBoardAgreement=${paroleBoardAgreementSummary.paroleBoardAgreementId}"></a>
			</td>
			<td><c:out value="${paroleBoardAgreementSummary.paroleBoardAgreementCategory}" /></td> 
				<td><fmt:formatDate 
						value="${paroleBoardAgreementSummary.startDate}" 
						pattern="MM/dd/yyyy" /> - <fmt:formatDate 
						value="${paroleBoardAgreementSummary.endDate}" pattern="MM/dd/yyyy" /> 
			</td>
		</tr>
		<tr id="conditions${paroleBoardAgreementSummary.paroleBoardAgreementId}" class="listTableRowNoHighlight" />
	</c:forEach>
</fmt:bundle>