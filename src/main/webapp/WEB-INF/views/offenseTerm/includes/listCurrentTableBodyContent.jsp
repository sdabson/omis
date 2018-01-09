<%--
  - List current table body content.
  -
  - Author: Josh Divine
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:forEach var="sentenceSummary" items="${sentenceSummaries}">
	<tr>
		<td><a href="${pageContext.request.contextPath}/offenseTerm/currentOffenseTermsActionMenu.html?sentence=${sentenceSummary.id}" class="actionMenuItem"></a></td>
		<td><a href="${pageContext.request.contextPath}/offenseTerm/edit.html?courtCase=${sentenceSummary.courtCaseId}"><c:out value="${sentenceSummary.docket}"/> - <c:out value="${sentenceSummary.courtName}"/></a></td>
		<td>
			<c:choose>
				<c:when test="${not empty sentenceSummary.convictionOffenseUrl}">
					<a href="${sentenceSummary.convictionOffenseUrl}" target="_blank"><c:out value="${sentenceSummary.convictionOffenseName}"/></a>
				</c:when>
				<c:otherwise>
					<c:out value="${sentenceSummary.convictionOffenseName}"/>
				</c:otherwise>
			</c:choose>
		</td>
		<td>	
			<c:set var="showPrisonTerm" value="${sentenceSummary.prisonRequirement.name eq 'REQUIRED' or sentenceSummary.prisonRequirement.name eq 'OPTIONAL'}" scope="request"/>
			<c:set var="prisonDays" value="${sentenceSummary.prisonTermDays}" scope="request"/>
			<c:set var="prisonMonths" value="${sentenceSummary.prisonTermMonths}" scope="request"/>
			<c:set var="prisonYears" value="${sentenceSummary.prisonTermYears}" scope="request"/>
			<c:set var="showProbationTerm" value="${sentenceSummary.probationRequirement.name eq 'REQUIRED' or sentenceSummary.probationRequirement.name eq 'OPTIONAL'}" scope="request"/>
			<c:set var="probationDays" value="${sentenceSummary.probationTermDays}" scope="request"/>
			<c:set var="probationMonths" value="${sentenceSummary.probationTermMonths}" scope="request"/>
			<c:set var="probationYears" value="${sentenceSummary.probationTermYears}" scope="request"/>
			<c:set var="showDeferredTerm" value="${sentenceSummary.deferredRequirement.name eq 'REQUIRED' or sentenceSummary.deferredRequirement.name eq 'OPTIONAL'}" scope="request"/>
			<c:set var="deferredDays" value="${sentenceSummary.deferredTermDays}" scope="request"/>
			<c:set var="deferredMonths" value="${sentenceSummary.deferredTermMonths}" scope="request"/>
			<c:set var="deferredYears" value="${sentenceSummary.deferredTermYears}" scope="request"/>
			<jsp:include page="/WEB-INF/views/sentence/includes/sentenceTerms.jsp"/>
		</td>
		<td><c:out value="${sentenceSummary.convictionCounts}"/></td>
		<td><c:out value="${sentenceSummary.legalDispositionCategoryName}"/></td>
		<td><fmt:formatDate value="${sentenceSummary.pronouncementDate}" pattern="MM/dd/yyyy"/></td>
	</tr>
</c:forEach>