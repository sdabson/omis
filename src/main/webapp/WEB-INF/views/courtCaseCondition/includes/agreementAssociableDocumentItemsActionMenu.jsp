<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.courtcasecondition.msgs.courtCaseConditions">
	<ul>
		<sec:authorize access="hasRole('COURT_CASE_CONDITION_CREATE') or hasRole('COURT_CASE_CONDITION_CREATE') or hasRole('ADMIN')">
			<li>
				<a id="createAgreementAsscociableDocumentItemLink" class="createLink" href="${pageContext.request.contextPath}/courtCaseCondition/createAgreementAssociableDocumentItem.html?agreementAssociableDocumentItemIndex=${agreementAssociableDocumentItemIndex}"><span class="visibleLinkLabel"><fmt:message key="addAgreementAssociableDocumentLink"/></span></a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>