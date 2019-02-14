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
  - Prison term offender header summary item.
  -
  - Author: Trevor Isles
  - Author: Ryan Johns
  - Author: Josh Divine
  - Version: 0.1.4 (Feb 11, 2019)
  - Since: OMIS 3.0
  --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.prisonterm.msgs.summary">
<c:if test="${not empty prisonTermSummary}">
<div class="offenderHeaderDetailsSection">
	<div class="headerCell">
		<span class="offenderHeaderFieldLabel"><fmt:message key="paroleEligibilityDateLabel"/></span>
		<a href="${pageContext.request.contextPath}/prisonTerm/list.html?offender=${offenderSummary.id}">
			<span class="offenderHeaderFieldValue"><fmt:formatDate value="${prisonTermSummary.paroleEligibilityDate}" pattern="MM/dd/yyyy"/></span>
		</a>
	</div>
	<div class="headerCell">
		<span class="offenderHeaderFieldLabel"><fmt:message key="prisonDischargeDateLabel"/></span>
		<a href="${pageContext.request.contextPath}/prisonTerm/list.html?offender=${offenderSummary.id}">
			<span class="offenderHeaderFieldValue"><fmt:formatDate value="${prisonTermSummary.projectedDischargeDate}" pattern="MM/dd/yyyy"/></span>
		</a>
	</div>
	<div class="headerCell">
		<c:if test="${prisonTermSummary.sentenceToFollow}">
		<span class="offenderHeaderField prisonTermSentenceToFollow"><fmt:message key="sentenceToFollowLabel"/></span>
		</c:if>
	</div>
</div>
</c:if>
</fmt:bundle>