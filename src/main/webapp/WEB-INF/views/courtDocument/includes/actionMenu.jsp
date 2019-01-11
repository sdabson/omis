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
 - Author: Ryan Johns
 - Author: Josh Divine
 - Version: 0.1.1 (Aug 8, 2018)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.courtdocument.msgs.document">
<ul>
	<li>
		<a href="${pageContext.request.contextPath}/courtCase/document/create.html?offender=${offender.id}" class="createLink">
			<fmt:message key="createLinkLabel"/>
		</a>
	</li>
	<sec:authorize access="hasRole('COURT_CASE_DOCUMENT_VIEW') or hasRole('ADMIN')">
	    <c:if test="${not empty offender}">
	        <li>
	            <a href="${pageContext.request.contextPath}/courtCase/document/courtDocumentListingReport.html?offender=${offender.id}&reportFormat=PDF" class="newTab printLink"><fmt:message key="courtDocumentListingReportLinkLabel"/></a>
	        </li>
	    </c:if>
	</sec:authorize>
</ul>
</fmt:bundle>