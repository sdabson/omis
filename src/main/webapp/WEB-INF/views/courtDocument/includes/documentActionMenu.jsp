<?xml version="1.0" encoding="UTF-8"?>
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
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.document.msgs.document" var="documentBundle"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.courtdocument.msgs.document">
<ul>
	<sec:authorize access="hasRole('COURT_CASE_DOCUMENT_EDIT') or hasRole('ADMIN')">
		<c:if test="${empty courtDocumentAssociation.docket}">
			<c:forEach items="${dockets}" var="docket" varStatus="status">
				<li>
					<a href="${pageContext.request.contextPath}/courtCase/document/associateDocket.html?courtDocumentAssociation=${courtDocumentAssociation.id}&docket=${docket.id}" class="viewEditLink">
						<fmt:message key="associateWithLinkLabel">
							<fmt:param value="${docket.value}"/>
		 					<fmt:param value="${docket.court.name}"/>
						</fmt:message>
					</a>
				</li>
			</c:forEach>
		</c:if>
	</sec:authorize>
	<li>
		<a href="${pageContext.request.contextPath}/documents/retrieveFile.html?document=${courtDocumentAssociation.document.id}" class="downloadLink">
			<fmt:message key="downloadLinkLabel" bundle="${documentBundle}"/>
		</a>
	</li>
	<li>
		<a href="${pageContext.request.contextPath}/courtCase/document/edit.html?courtDocumentAssociation=${courtDocumentAssociation.id}" class="viewEditLink">
			<fmt:message key="viewEditLinkLabel"/>
		</a>
	</li>
	<li>
		<a href="${pageContext.request.contextPath}/courtCase/document/remove.html?courtDocumentAssociation=${courtDocumentAssociation.id}" class="removeLink">
			<fmt:message key="removeLink" bundle="${commonBundle}"/>
		</a>
	</li>
		<sec:authorize access="hasRole('COURT_CASE_DOCUMENT_VIEW') or hasRole('ADMIN')">
	    <c:if test="${not empty courtDocumentAssociation}">
	        <li>
	            <a href="${pageContext.request.contextPath}/courtCase/document/courtDocumentDetailsReport.html?courtDocumentAssociation=${courtDocumentAssociation.id}&reportFormat=PDF" class="newTab printLink"><fmt:message key="courtDocumentDetailsReportLinkLabel"/></a>
	        </li>
	    </c:if>
	</sec:authorize>
</ul>
</fmt:bundle>