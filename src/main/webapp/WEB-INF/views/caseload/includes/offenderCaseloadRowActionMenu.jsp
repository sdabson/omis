<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.caseload.msgs.caseload">
	<ul>
			<li>
				<a class="createLink" href="${pageContext.request.contextPath}/caseload/offenderContact/create.html?offenderAssignment=${offenderAssignment.id}"><fmt:message key="createOffenderContactLabel"/></a>			
			</li>
			<sec:authorize access="hasRole('CASELOAD_OFFENDER_CONTACT_VIEW') or hasRole('ADMIN')">
			<c:if test="${caseloadContactExists}">		
				<li>
					<a class="listLink viewContactsLink" href="${pageContext.request.contextPath}/caseload/showAssociatedContacts.html?offenderAssignment=${offenderAssignment.id}" id="viewContacts${offender.id}"><fmt:message key="contactsListLabel"/></a>
				</li>
			</c:if>		
			</sec:authorize>	
			<li>
				<a class="offenderProfileLink newTab" href="${pageContext.request.contextPath}/offender/profile.html?offender=${offenderAssignment.offender.id}"><fmt:message key="loadOffenderContactLabel"/></a>			
			</li>
			<li>
				<a class="viewEditLink" href="${pageContext.request.contextPath}/caseload/reassignOffender.html?offenderAssignment=${offenderAssignment.id}"><fmt:message key="reassignOffenderLabel"/></a>			
			</li>
	</ul>
</fmt:bundle>