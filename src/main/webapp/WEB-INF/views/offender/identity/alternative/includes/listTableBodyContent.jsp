<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="/WEB-INF/tld/omis.tld" prefix="omis" %>
<%@ taglib uri="/WEB-INF/tld/person.tld" prefix="person" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.person.msgs.name" var="nameBundle"/>
<fmt:setBundle basename="omis.demographics.msgs.demographics" var="demographicsBundle"/>
<fmt:setBundle basename="omis.person.msgs.identity" var="identityBundle"/>
<c:forEach var="alternativeIdentityAssociation" items="${alternativeIdentityAssociations}" varStatus="status">
	<c:choose>
		<c:when test="${omis:isDateRangeActive(alternativeIdentityAssociation.dateRange, currentDate)}">
			<c:set var="activeClass" value="activeRecord"/>
		</c:when>
		<c:otherwise>
			<c:set var="activeClass" value="inactiveRecord"/>
		</c:otherwise>
	</c:choose>
	<tr class="${activeClass}" id="alternativeIdentityRow${status.index}">
		<td>
			<a class="actionMenuItem rowActionMenuLinks" id="summaryActionMenuLinks${status.index}" href="${pageContext.request.contextPath}/offender/identity/alternative/alternativeIdentityRowActionMenu.html?alternativeIdentityAssociation=${alternativeIdentityAssociation.id}"></a>
		</td>
		<td>
			<c:if test="${alternativeIdentityAssociation.alternativeNameAssociation ne null}">
			<fmt:message key="fullNameLabel" bundle="${nameBundle}">
				<c:choose>
					<c:when test="${not empty alternativeIdentityAssociation.alternativeNameAssociation.name.middleName}">
						<c:set value="${alternativeIdentityAssociation.alternativeNameAssociation.name.middleName}" var="middleName"/>						
					</c:when>
					<c:otherwise>
						<c:set value="" var="middleName"/>
					</c:otherwise>					
				</c:choose>
				<fmt:param value="${alternativeIdentityAssociation.alternativeNameAssociation.name.lastName}"/>
				<fmt:param value="${alternativeIdentityAssociation.alternativeNameAssociation.name.firstName}"/>
				<fmt:param value="${middleName}"/>
				<fmt:param value="${alternativeIdentityAssociation.alternativeNameAssociation.category.name}"/>
			</fmt:message>
			</c:if>
		</td>
		<sec:authorize access="hasRole('OFFENDER_ALT_IDENTITY_ASSOC_SSN_VIEW') or hasRole('ADMIN')" var="hasSsnRole"/>
			<td>							
				<person:formatSsn value="${alternativeIdentityAssociation.identity.socialSecurityNumber}" masked="${not hasSsnRole}"/>
			</td>
		<td><c:out value="${alternativeIdentityAssociation.identity.stateIdNumber}"/></td>
		<td><fmt:formatDate value="${alternativeIdentityAssociation.identity.birthDate}" pattern="MM/dd/yyyy"/></td>
		<td><c:out value="${alternativeIdentityAssociation.identity.birthCountry.name}"/></td>
		<td><c:out value="${alternativeIdentityAssociation.identity.birthPlace.name}"/></td>
		<td>
			<c:if test="${not empty alternativeIdentityAssociation.identity.sex}">
			<fmt:message key="sex${alternativeIdentityAssociation.identity.sex.name}Label" bundle="${demographicsBundle}"/>
			</c:if>
		</td>
		<td><c:out value="${alternativeIdentityAssociation.category.name}"/></td>
		<td><fmt:formatDate value="${alternativeIdentityAssociation.dateRange.startDate}" pattern="MM/dd/yyyy"/></td>
		<td><fmt:formatDate value="${alternativeIdentityAssociation.dateRange.endDate}" pattern="MM/dd/yyyy"/></td>
	</tr>
</c:forEach>