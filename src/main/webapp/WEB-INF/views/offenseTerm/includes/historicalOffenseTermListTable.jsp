<%--
 - OMIS - Offender Management Information System
 - Copyright (C) 2011 - 2017 State of Montana
 -
 - This program is free software: you can redistribute it and/or modify
 - it under the terms of the GNU General Public License as published by
 - the Free Software Foundation, either version 3 of the License, or
 - (at your option) any later version.
 -
 - This program is distributed in the hope that it will be useful,
 - but WITHOUT ANY WARRANTY; without even the implied warranty of
 - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 - GNU General Public License for more details.
 -
 - You should have received a copy of the GNU General Public License
 - along with this program.  If not, see <http://www.gnu.org/licenses/>.
 --%>

<%--
  - List table of historical offense terms.
  -
  - Historical offense terms are inactive sentences.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle var="sentenceBundle" basename="omis.sentence.msgs.sentence"/>
<fmt:setBundle var="convictionBundle" basename="omis.conviction.msgs.conviction"/>
<fmt:setBundle var="offenseTermBundle" basename="omis.offenseterm.msgs.offenseTerm"/>
<table class="listTable">
	<tbody>
		<c:forEach var="inactiveSentenceSummary" items="${inactiveSentenceSummaries}">
			<tr>
				<th><fmt:message key="offenseLabel" bundle="${convictionBundle}"/></th>
				<th><fmt:message key="termLabel" bundle="${offenseTermBundle}"/>
				<th><fmt:message key="pronouncementDateLabel" bundle="${sentenceBundle}"/>
				<th><fmt:message key="dateLabel" bundle="${convictionBundle}"/>
				<th><fmt:message key="changeOrderLabel" bundle="${sentenceBundle}"/>
			</tr>
			<tr>
				<td><c:out value="${inactiveSentenceSummary.convictionOffenseName}"/></td>
				<td>
					<c:set var="showPrisonTerm" value="${'OPTIONAL' eq inactiveSentenceSummary.prisonRequirement.name or 'REQUIRED' eq inactiveSentenceSummary.prisonRequirement.name}" scope="request"/>
					<c:set var="prisonYears" value="${inactiveSentenceSummary.prisonTermYears}" scope="request"/>
					<c:set var="prisonMonths" value="${inactiveSentenceSummary.prisonTermMonths}" scope="request"/>
					<c:set var="prisonDays" value="${inactiveSentenceSummary.prisonTermDays}" scope="request"/>
					<c:set var="showProbationTerm" value="${'OPTIONAL' eq inactiveSentenceSummary.probationRequirement.name or 'REQUIRED' eq inactiveSentenceSummary.probationRequirement.name}" scope="request"/>
					<c:set var="probationYears" value="${inactiveSentenceSummary.probationTermYears}" scope="request"/>
					<c:set var="probationMonths" value="${inactiveSentenceSummary.probationTermMonths}" scope="request"/>
					<c:set var="probationDays" value="${inactiveSentenceSummary.probationTermDays}" scope="request"/>
					<c:set var="showDeferredTerm" value="${'OPTIONAL' eq inactiveSentenceSummary.deferredRequirement.name or 'REQUIRED' eq inactiveSentenceSummary.deferredRequirement.name}" scope="request"/>
					<c:set var="deferredYears" value="${inactiveSentenceSummary.deferredTermYears}" scope="request"/>
					<c:set var="deferredMonths" value="${inactiveSentenceSummary.deferredTermMonths}" scope="request"/>
					<c:set var="deferredDays" value="${inactiveSentenceSummary.deferredTermDays}" scope="request"/>
					<jsp:include page="/WEB-INF/views/sentence/includes/sentenceTerms.jsp"/>
				</td>
				<td><fmt:formatDate value="${inactiveSentenceSummary.pronouncementDate}" pattern="MM/dd/yyyy"/></td>
				<td><fmt:formatDate value="${inactiveSentenceSummary.convictionDate}" pattern="MM/dd/yyyy"/></td>
				<td><c:out value="${inactiveSentenceSummary.changeOrder}"/></td>
			</tr>
			<tr>
				<th><fmt:message key="legalDispositionCategoryLabel" bundle="${sentenceBundle}"/></th>
				<th><fmt:message key="sentenceCategoryLabel" bundle="${sentenceBundle}"/>
				<th><fmt:message key="countsLabel" bundle="${convictionBundle}"/></th>
				<th><fmt:message key="jailTimeCreditShortLabel" bundle="${sentenceBundle}"/>
				<th><fmt:message key="streetTimeCreditShortLabel" bundle="${sentenceBundle}"/>
			</tr>
			<tr>
				<td><c:out value="${inactiveSentenceSummary.legalDispositionCategoryName}"/></td>
				<td><c:out value="${inactiveSentenceSummary.categoryName}"/></td>
				<td><c:out value="${inactiveSentenceSummary.convictionCounts}"/></td>
				<td><c:out value="${inactiveSentenceSummary.jailTimeCredit}"/></td>
				<td><c:out value="${inactiveSentenceSummary.streetTimeCredit}"/></td>
			</tr>
		</c:forEach>
	</tbody>
</table>