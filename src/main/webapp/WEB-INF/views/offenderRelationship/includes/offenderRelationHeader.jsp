<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<fmt:setBundle basename="omis.offenderrelationship.msgs.createOffenderRelationship" var="relationshipBundle"/>
<fmt:setBundle basename="omis.demographics.msgs.demographics" var="demographicsBundle"/>
<fmt:setBundle basename="omis.person.msgs.person" var="personBundle"/>
<div id="offenderRelationshipSummary" class="summaryHeader">
	<p>
		<a class="offenderRelationProfileLink" href="${pageContext.request.contextPath}/offenderRelationship/profile.html?relation=${offenderRelationshipSummary.id}" class="offenderRelationshipProfileLink" title="<fmt:message key='offenderRelationshipProfileLink' bundle='${relationshipBundle}'/>"><span class="linkLabel"><fmt:message key="offenderRelationshipProfileLink" bundle="${relationshipBundle}"/></span></a>
		<span class="headerLabel"><fmt:message key="offenderRelationNameLabel" bundle="${relationshipBundle}"/></span>
		<span class="headerValue">
			<c:out value="${offenderRelationshipSummary.lastName}"/>,
			<c:out value="${offenderRelationshipSummary.firstName}"/>
			<c:if test="${not empty offenderRelationshipSummary.middleName}">
				<c:out value="${fn:substring(offenderRelationshipSummary.middleName, 0, 1)}"/>
			</c:if>
			<c:if test="${not empty offenderRelationshipSummary.suffix}">
				<c:out value="${offenderRelationshipSummary.suffix}"/>
			</c:if>
		</span> 
		<c:if test="${not empty offenderRelationshipSummary.sex}"> 
 			<span class="headerLabel"><fmt:message key="sexLabel" bundle="${demographicsBundle}"/></span> 
			<span class="headerValue"><fmt:message key="sexLabel.${offenderRelationshipSummary.sex.name}" bundle="${demographicsBundle}"/></span> 
 		</c:if> 
 		<c:if test="${not empty offenderRelationshipSummary.birthDate}"> 
 			<span class="headerLabel"><fmt:message key="birthDateLabel" bundle="${personBundle}"/></span> 
 			<span class="headerValue"><fmt:formatDate value="${offenderRelationshipSummary.birthDate}" pattern="MM/dd/yyyy"/></span> 
 		</c:if> 
	</p>
</div>