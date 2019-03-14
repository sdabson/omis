<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle" />
<fmt:bundle basename="omis.probationparole.msgs.probationParoleDocument">
	<ul>
		<sec:authorize access="hasRole('PROBATION_PAROLE_DOCUMENT_CREATE') or hasRole('ADMIN')">
			<li>
				<a class="createLink" href="${pageContext.request.contextPath}/probationParole/document/create.html?offender=${offender.id}"><span class="visibleLinkLabel"><fmt:message key="createProbationParoleDocumentLink"/></span></a>
			</li>
		</sec:authorize>
		<sec:authorize access="hasRole('PROBATION_PAROLE_DOCUMENT_LIST') or hasRole('ADMIN')">
			<c:if test="${not empty offender}">
				<li>
					<a href="${pageContext.request.contextPath}/probationParole/document/ppDocListingReport.html?offender=${offender.id}&reportFormat=PDF" class="newTab printLink"><fmt:message key="ppDocListingReportLinkLabel"/></a>
				</li>
			</c:if>
		</sec:authorize>		
	</ul>
</fmt:bundle>