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
<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.document.msgs.document" var="documentBundle"/>
<fmt:bundle basename="omis.earlyreleasetracking.msgs.earlyReleaseRequest">
	<c:choose>
		<c:when test="${earlyReleaseRequestDocumentAssociationItem.itemOperation == 'REMOVE'}">
			<c:set value="removeContent" var="displayClass"/>
		</c:when>
		<c:otherwise>
			<c:set var="displayClass" value=""/>
		</c:otherwise>
	</c:choose>
	<span id="earlyReleaseRequestDocumentAssociationItem${earlyReleaseRequestDocumentAssociationItemIndex}" class="earlyReleaseRequestDocumentAssociationItem ${displayClass}">
		<a class="removeLink" id="removeEarlyReleaseRequestDocumentAssociationLink${earlyReleaseRequestDocumentAssociationItemIndex}"
		href="${pageContext.request.contextPath}/earlyReleaseTracking/removeEarlyReleaseRequestDocumentAssociation.html">
			<fmt:message key="removeLink" bundle="${commonBundle}"/>
		</a>
		<input type="hidden" id="earlyReleaseRequestDocumentAssociationId${earlyReleaseRequestDocumentAssociationItemIndex}"
				name="earlyReleaseRequestDocumentAssociationItems[${earlyReleaseRequestDocumentAssociationItemIndex}].earlyReleaseRequestDocumentAssociation"
				value="${earlyReleaseRequestDocumentAssociationItem.earlyReleaseRequestDocumentAssociation.id}"/>
		<input type="hidden" name="earlyReleaseRequestDocumentAssociationItems[${earlyReleaseRequestDocumentAssociationItemIndex}].document"
						id="earlyReleaseRequestDocumentAssociationItems[${earlyReleaseRequestDocumentAssociationItemIndex}].document"
						value="${earlyReleaseRequestDocumentAssociationItems[earlyReleaseRequestDocumentAssociationItemIndex].document.id}"/>
		<form:errors path="earlyReleaseRequestDocumentAssociationItems[${earlyReleaseRequestDocumentAssociationItemIndex}].earlyReleaseRequestDocumentAssociation" cssClass="error"/>
		<input type="hidden" id="earlyReleaseRequestDocumentAssociationOperation${earlyReleaseRequestDocumentAssociationItemIndex}"
				name="earlyReleaseRequestDocumentAssociationItems[${earlyReleaseRequestDocumentAssociationItemIndex}].itemOperation" value="${earlyReleaseRequestDocumentAssociationItem.itemOperation}"/>
		<form:errors path="earlyReleaseRequestDocumentAssociationItems[${earlyReleaseRequestDocumentAssociationItemIndex}].itemOperation" cssClass="error"/>
		<span class="fieldGroup">
			<label for="earlyReleaseRequestDocumentAssociationItems[${earlyReleaseRequestDocumentAssociationItemIndex}].title" class="fieldLabel">
				<fmt:message key="documentTitleLabel"/>
			</label>
			<input type="text" id="earlyReleaseRequestDocumentAssociationItems[${earlyReleaseRequestDocumentAssociationItemIndex}].title"
					name="earlyReleaseRequestDocumentAssociationItems[${earlyReleaseRequestDocumentAssociationItemIndex}].title"
				value="${earlyReleaseRequestDocumentAssociationItems[earlyReleaseRequestDocumentAssociationItemIndex].title}" />
			<form:errors path="earlyReleaseRequestDocumentAssociationItems[${earlyReleaseRequestDocumentAssociationItemIndex}].title" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<label for="earlyReleaseRequestDocumentAssociationItems[${earlyReleaseRequestDocumentAssociationItemIndex}].date" class="fieldLabel">
				<fmt:message key="fileDateLabel"/>
			</label>
			<fmt:formatDate var="earlyReleaseRequestDocumentAssociationDate" value="${earlyReleaseRequestDocumentAssociationItem.date}" pattern="MM/dd/yyyy"/>
			<input type="text" value="${earlyReleaseRequestDocumentAssociationDate}" id="earlyReleaseRequestDocumentAssociationItems[${earlyReleaseRequestDocumentAssociationItemIndex}].date"
					name="earlyReleaseRequestDocumentAssociationItems[${earlyReleaseRequestDocumentAssociationItemIndex}].date" class="date" />
			<form:errors path="earlyReleaseRequestDocumentAssociationItems[${earlyReleaseRequestDocumentAssociationItemIndex}].date" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<a id="createDocumentTagItemLink${earlyReleaseRequestDocumentAssociationItemIndex}" class="createLink addLink">
				<span class="visibleLinkLabel">
					<fmt:message key="addTagLink"/>
				</span>
			</a>
		</span>
		<span id="documentTagItems${earlyReleaseRequestDocumentAssociationItemIndex}">
			<c:forEach var="documentTagItem" items="${earlyReleaseRequestDocumentAssociationItems[earlyReleaseRequestDocumentAssociationItemIndex].documentTagItems}" varStatus="status">
				<c:set var="documentTagItem" value="${documentTagItem}" scope="request"/>
				<c:set var="documentTagItemIndex" value="${status.index}" scope="request"/>
				<c:if test="${not empty documentTagItem.documentTagOperation}">
					<jsp:include page="documentTagItemContent.jsp"/>
				</c:if>
			</c:forEach>
		</span>
		<span class="fieldGroup">
			<label for="earlyReleaseRequestDocumentAssociationItems[${earlyReleaseRequestDocumentAssociationItemIndex}].data" class="fieldLabel">
				<fmt:message key="documentLabel"/>
			</label>
			<c:choose>
				<c:when test="${not empty earlyReleaseRequestDocumentAssociationItem.document}">
					<c:set var="filename" value = "${earlyReleaseRequestDocumentAssociationItems[earlyReleaseRequestDocumentAssociationItemIndex].document.filename}"/>
					<fmt:formatDate value="${earlyReleaseRequestDocumentAssociationItems[earlyReleaseRequestDocumentAssociationItemIndex].document.date}"
							pattern="MM/dd/yyyy" var="documentDate"/>
					<a id="earlyReleaseRequestDocumentAssociationDownloadLink${earlyReleaseRequestDocumentAssociationItemIndex}"
		 					href="${pageContext.request.contextPath}/earlyReleaseTracking/retrieveFile.html?document=${earlyReleaseRequestDocumentAssociationItems[earlyReleaseRequestDocumentAssociationItemIndex].document.id}"
							class="downloadLink">
						<fmt:message key="titleExtensionFormat" bundle="${documentBundle}">
							<fmt:param value="${filename}"/><fmt:param value="${documentDate}"/>
						</fmt:message>
					</a>
					<input type="hidden" name="earlyReleaseRequestDocumentAssociationItems[${earlyReleaseRequestDocumentAssociationItemIndex}].data"
						id="earlyReleaseRequestDocumentAssociationItems[${earlyReleaseRequestDocumentAssociationItemIndex}].data"
						value="${earlyReleaseRequestDocumentAssociationItems[earlyReleaseRequestDocumentAssociationItemIndex].data}"/>
				</c:when>
				<c:otherwise>
						<input id="earlyReleaseRequestDocumentAssociationItems[${earlyReleaseRequestDocumentAssociationItemIndex}].data" type="file"
								name="earlyReleaseRequestDocumentAssociationItems[${earlyReleaseRequestDocumentAssociationItemIndex}].data" />
				</c:otherwise>
			</c:choose>
			<input type="hidden" name="earlyReleaseRequestDocumentAssociationItems[${earlyReleaseRequestDocumentAssociationItemIndex}].fileExtension"
					id="earlyReleaseRequestDocumentAssociationItems[${earlyReleaseRequestDocumentAssociationItemIndex}].fileExtension"
					value="${earlyReleaseRequestDocumentAssociationItems[earlyReleaseRequestDocumentAssociationItemIndex].fileExtension}"/>
			<form:errors path="earlyReleaseRequestDocumentAssociationItems[${earlyReleaseRequestDocumentAssociationItemIndex}].data" cssClass="error"/>
		</span>
	</span>
</fmt:bundle>