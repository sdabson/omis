<%--
  - Action menu for offense terms.
  -
  - Author: Josh Divine
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.offenseterm.msgs.offenseTerm" var="offenseTermBundle"/>
<ul>
	<c:if test="${not empty person}">
		<sec:authorize access="hasRole('OFFENSE_TERM_CREATE') or hasRole('ADMIN')">
			<li>
				<a href="${pageContext.request.contextPath}/offenseTerm/create.html?person=${person.id}" class="createLink"><span class="visibleLinkLabel"><fmt:message key="createCourtCaseLink" bundle="${offenseTermBundle}"/></span></a>
			</li>
		</sec:authorize>
	</c:if>
	<c:if test="${not empty person}">
		<sec:authorize access="hasRole('OFFENSE_TERM_VIEW') or hasRole('ADMIN')">
			<li>
				<a href="${pageContext.request.contextPath}/offenseTerm/currentCourtCaseListingReport.html?person=${person.id}&reportFormat=PDF" class="newTab reportLink"><fmt:message key="courtCaseCurrentListingReportLinkLabel" bundle="${offenseTermBundle}"/></a>
			</li>
		</sec:authorize>
	</c:if>
	<c:if test="${not empty sentence}">
		<sec:authorize access="hasRole('OFFENSE_TERM_VIEW') or hasRole('ADMIN')">
			<li>
				<a href="${pageContext.request.contextPath}/offenseTerm/edit.html?courtCase=${sentence.conviction.courtCase.id}&amp;expandedSentence=${sentence.id}#sentenceOperations[${sentence.id}]" class="viewEditLink"><span class="visibleLinkLabel"><fmt:message key="viewEditOffenseLink" bundle="${offenseTermBundle}"/></span></a>
			</li>
		</sec:authorize>
	</c:if>
	<c:if test="${not empty sentence}">
		<sec:authorize access="hasRole('DISABLED')">
			<li>
				<a href="${pageContext.request.contextPath}/offenseTerm/copySentence.html?sentence=${sentence.id}" class="viewEditLink"><span class="visibleLinkLabel"><fmt:message key="copyOffenseLink" bundle="${offenseTermBundle}"/></span></a>
			</li>
		</sec:authorize>
	</c:if>
	<c:if test="${not empty sentence}">
		<sec:authorize access="hasRole('OFFENSE_TERM_REMOVE') or hasRole('ADMIN')">
			<li>
				<a href="${pageContext.request.contextPath}/offenseTerm/removeCurrentOffense.html?conviction=${sentence.conviction.id}" class="removeLink"><span class="visibleLinkLabel"><fmt:message key="removeOffenseLink" bundle="${offenseTermBundle}"/></span></a>
			</li>
		</sec:authorize>
	</c:if>
	<c:if test="${not empty sentence}">
		<sec:authorize access="hasRole('OFFENSE_TERM_VIEW') or hasRole('ADMIN')">
			<li>
				<a href="${pageContext.request.contextPath}/offenseTerm/courtCaseDetailsReport.html?courtCase=${sentence.conviction.courtCase.id}&reportFormat=PDF" class="newTab reportLink"><fmt:message key="courtCaseDetailsReportLinkLabel" bundle="${offenseTermBundle}"/></a>
			</li>
		</sec:authorize>
	</c:if>
	<c:if test="${not empty sentence}">
		<sec:authorize access="hasRole('OFFENSE_TERM_VIEW') or hasRole('ADMIN')">
			<li>
				<a href="${pageContext.request.contextPath}/offenseTerm/reportOfViolationReport.rtf?courtCase=${sentence.conviction.courtCase.id}&reportFormat=RTF" class="reportLink"><fmt:message key="reportOfViolationReportLinkLabel" bundle="${offenseTermBundle}"/></a>
			</li>
		</sec:authorize>
	</c:if>		
</ul>