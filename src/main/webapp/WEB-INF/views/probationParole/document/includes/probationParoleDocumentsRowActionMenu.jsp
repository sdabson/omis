<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle" />
<fmt:setBundle basename="omis.document.msgs.document" var="documentBundle" />
<fmt:bundle basename="omis.probationparole.msgs.probationParoleDocument">
	<ul>
		<sec:authorize access="hasRole('PROBATION_PAROLE_DOCUMENT_VIEW') or hasRole('ADMIN')">
			<li>
				<a href="${pageContext.request.contextPath}/probationParole/document/retrieveFile.html?document=${probationParoleDocumentAssociation.document.id}" class="downloadLink">
					<fmt:message key="downloadLinkLabel" bundle="${documentBundle}"/>
				</a>
			</li>
			<li>
				<a class="viewEditLink" href="${pageContext.request.contextPath}/probationParole/document/edit.html?probationParoleDocumentAssociation=${probationParoleDocumentAssociation.id}"><span class="visibleLinkLabel"><fmt:message key="viewEditLink" bundle="${commonBundle}"/></span></a>
			</li>
		</sec:authorize>
		<sec:authorize access="hasRole('PROBATION_PAROLE_DOCUMENT_REMOVE') or hasRole('ADMIN')">
			<li>
				<a class="removeLink" href="${pageContext.request.contextPath}/probationParole/document/remove.html?probationParoleDocumentAssociation=${probationParoleDocumentAssociation.id}"><span class="visibleLinkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
			</li>
		</sec:authorize>
		<sec:authorize access="hasRole('PROBATION_PAROLE_DOCUMENT_VIEW') or hasRole('ADMIN')">
			<li>
				<a href="${pageContext.request.contextPath}/probationParole/document/ppDocDetailsReport.html?probationParoleDocumentAssociation=${probationParoleDocumentAssociation.id}&reportFormat=PDF" class="newTab printLink"><fmt:message key="ppDocDetailsReportLinkLabel"/></a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>