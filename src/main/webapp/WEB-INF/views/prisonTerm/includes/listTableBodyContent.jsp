<%--
 - Table body content of prison terms.
 -
 - Author: Trevor Isles
 --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.prisonterm.msgs.prisonTerm">
<c:forEach var="prisonTerm" items="${prisonTerms}" varStatus="status">
	<tr class="${activeClass}">
		<td>
			<a class="actionMenuItem" id="rowActionMenuLink${status.index}" href="${pageContext.request.contextPath}/prisonTerm/prisonTermsActionMenu.html?prisonTerm=${prisonTerm.id}&offender=${offender.id}"></a>
		</td>
		<td><fmt:formatDate value="${prisonTerm.actionDate}" pattern="MM/dd/yyyy"/></td>
		<td><fmt:formatDate value="${prisonTerm.paroleEligibilityDate}" pattern="MM/dd/yyyy"/></td>
		<td><fmt:formatDate value="${prisonTerm.projectedDischargeDate}" pattern="MM/dd/yyyy"/></td>
		<td><c:choose>
				<c:when test="${paroleElgibilitySummary.sentenceToFollow}">
					<fmt:message key="yesLabel" bundle="${commonBundle}"/>
				</c:when>
				<c:otherwise>
					<fmt:message key="noLabel" bundle="${commonBundle}"/>
				</c:otherwise>
			</c:choose>
		</td>
		<td><c:if test="${not empty prisonTermSummary.verificationUserUsername}">
			<c:out value="${prisonTermSummary.verificationUserLastName}, ${prisonTermSummary.verificationUserFirstName} (${prisonTermSummary.verificationUserUsername})"/></c:if></td>
		<td><fmt:formatDate value="${prisonTerm.verificationDate}" pattern="MM/dd/yyyy"/></td>
		<td><c:if test="${not empty prisonTerm.status}"><fmt:message key="prisonTermStatusLabel.${prisonTerm.status}"/></c:if></td>
	</tr>
</c:forEach>
</fmt:bundle>