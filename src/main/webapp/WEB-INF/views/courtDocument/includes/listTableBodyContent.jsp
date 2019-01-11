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
 - Version: 0.1.1 (Aug 7, 2018)
 - Since: OMIS 3.0
 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle var="documentBundle" basename="omis.document.msgs.document" />
<fmt:bundle basename="omis.courtdocument.msgs.document">
<c:forEach items="${courtDocumentAssociationSummaries}" var="courtDocumentAssociationSummary" varStatus="status">
	<tr>
		<td>
			<a href="${pageContext.request.contextPath}/courtCase/document/courtDocumentAssociationRowActionMenu.html?courtDocumentAssociation=${courtDocumentAssociationSummary.courtDocumentAssociationId}" id="courtDocument${status.index}" class="actionMenuItem"></a>
		</td>
		<td>
			<fmt:formatDate value="${courtDocumentAssociationSummary.fileDate}" pattern="MM/dd/yyyy" var="fileDate"/>
			<c:out value="${fileDate}"/>
		</td>
		<td>
			<c:out value="${courtDocumentAssociationSummary.docketName}"/>
		</td>
		<td>
			<c:out value="${courtDocumentAssociationSummary.courtName}"/>
		</td>
		<td>
			<c:out value="${courtDocumentAssociationSummary.categoryName}"/>
		</td>
		<td>
			<c:out value="${courtDocumentAssociationSummary.documentTitle}"/>
		</td>
		<td>
			<fmt:message key="uploadDetailFormat" bundle="${documentBundle}">
				<c:choose>
					<c:when test="${not empty courtDocumentAssociationSummary.updateUserMiddleName}">
						<c:set value="${courtDocumentAssociationSummary.updateUserMiddleName}" var="middleName"/>
					</c:when>
					<c:otherwise>
						<c:set value="" var="middleName"/>
					</c:otherwise>
				</c:choose>
				<fmt:param value="${courtDocumentAssociationSummary.updateUserFirstName}"/>
				<fmt:param value="${middleName}"/>
				<fmt:param value="${courtDocumentAssociationSummary.updateUserLastName}"/>
				<fmt:formatDate value="${courtDocumentAssociationSummary.updateDate}" pattern="MM/dd/yyyy" var="updateDate"/>
				<fmt:param value="${updateDate}"/>
			</fmt:message>
		</td>	
	</tr>
	</c:forEach>
</fmt:bundle>