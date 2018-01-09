<%--
 - Table body content to list referrals requiring authorization but not yet
 - authorized. 
 -
 - Author: Stephen Abson
 --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.health.msgs.health" var="healthBundle"/>
<c:forEach var="pendingAuthorization" items="${pendingAuthorizations}">
	<tr>
		<td>
			<c:if test="${pendingAuthorization.type.name eq 'EXTERNAL_MEDICAL'}">
				<sec:authorize access="hasRole('ADMIN') or hasRole('HEALTH_ADMIN') or hasRole('EXTERNAL_REFERRAL_AUTHORIZATION_REQUEST_EDIT') or hasRole('EXTERNAL_REFERRAL_AUTHORIZATION_REQUEST_VIEW')">
					<a href="${pageContext.request.contextPath}/health/referral/external/authorization/request/edit.html?request=${pendingAuthorization.id}" class="authorizeLink" title="<fmt:message key='editExternalReferralAuthorizationRequestLink' bundle='${healthBundle}'/>"><span class="linkLabel"><fmt:message key="editExternalReferralAuthorizationRequestLink" bundle="${healthBundle}"/></span></a>
				</sec:authorize>
			</c:if>
		</td>
		<td>
			<c:out value="${pendingAuthorization.offenderLastName}"/>,
			<c:out value="${pendingAuthorization.offenderFirstName}"/>
			<c:out value="${pendingAuthorization.offenderMiddleName}"/> 
			(<c:out value="${pendingAuthorization.offenderNumber}"/>)
		</td>
		<td>
			<c:if test="${pendingAuthorization.primaryProviderExists}">
				<c:out value="${pendingAuthorization.primaryProviderLastName}"/>,
				<c:out value="${pendingAuthorization.primaryProviderFirstName}"/>  
				<c:out value="${pendingAuthorization.primaryProviderTitleAbbreviation}"/>
			</c:if>
		</td>
		<td>
			<c:out value="${pendingAuthorization.reasonName}"/>
		</td>
		<td>
			<c:if test="${not empty pendingAuthorization.reasonNotes}">
				<a class="commentLink" title="<c:out value='${pendingAuthorization.reasonNotes}'/>"></a>
			</c:if>
		</td>
		<td>
			<c:out value="${pendingAuthorization.medicalFacilityName}"/>
		</td>
		<td>
			<c:if test="${pendingAuthorization.referredByProviderExists}">
				<c:out value="${pendingAuthorization.referredByProviderLastName}"/>,
				<c:out value="${pendingAuthorization.referredByProviderFirstName}"/>  
				<c:out value="${pendingAuthorization.referredByProviderTitleAbbreviation}"/>
			</c:if>
		</td>
		<td>
			<fmt:formatDate value="${pendingAuthorization.referredDate}" pattern="MM/dd/yyyy"/>
		</td>
	</tr>
</c:forEach>