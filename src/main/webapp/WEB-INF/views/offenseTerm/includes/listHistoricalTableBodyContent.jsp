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
  - Table body content for historical offense terms.
  -
  - Historical offense terms are inactive sentences.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:forEach var="inactiveSentenceSummary" items="${inactiveSentenceSummaries}">
<tr>
	<td><a href="${pageContext.request.contextPath}/offenseTerm/historicalOffenseTermsActionMenu.html?sentence=${inactiveSentenceSummary.id}" class="actionMenuItem"></a></td>
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
</c:forEach>