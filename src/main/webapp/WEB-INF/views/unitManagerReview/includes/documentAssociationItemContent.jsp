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
 - Document association fields for unit manager reviews.
 -
 - Author: Josh Divine
 - Version: 0.1.0 (Jan 30, 2018)
 - Since: OMIS 3.0
 --%><?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.document.msgs.document" var="documentBundle"/>
<fmt:bundle basename="omis.unitmanagerreview.msgs.unitManagerReview">
	<span id="unitManagerReviewDocumentAssociationItem${documentAssociationItemIndex}" class="documentItem">
		<a class="removeLink" id="removeUnitManagerReviewDocumentAssociationLink${documentAssociationItemIndex}"
		href="${pageContext.request.contextPath}/unitManagerReview/removeUnitManagerReviewDocumentAssociation.html?unitManagerReview=${unitManagerReview.id}">
			<fmt:message key="removeLink" bundle="${commonBundle}"/>
		</a>
		<input type="hidden" id="unitManagerReviewDocumentAssociationId${documentAssociationItemIndex}"
				name="unitManagerReviewDocumentAssociationItems[${documentAssociationItemIndex}].unitManagerReviewDocumentAssociation"
				value="${unitManagerReviewDocumentAssociationItem.unitManagerReviewDocumentAssociation.id}"/>
		<form:errors path="unitManagerReviewDocumentAssociationItems[${documentAssociationItemIndex}].unitManagerReviewDocumentAssociation" cssClass="error"/>
		<input type="hidden" id="unitManagerReviewDocumentAssociationOperation${documentAssociationItemIndex}"
				name="unitManagerReviewDocumentAssociationItems[${documentAssociationItemIndex}].itemOperation" value="${unitManagerReviewDocumentAssociationItem.itemOperation}"/>
		<form:errors path="unitManagerReviewDocumentAssociationItems[${documentAssociationItemIndex}].itemOperation" cssClass="error"/>
		
		<span class="fieldGroup">
			<label for="unitManagerReviewDocumentAssociationItems[${documentAssociationItemIndex}].title" class="fieldLabel">
				<fmt:message key="documentTitleLabel"/>
			</label>
			<input type="text" id="unitManagerReviewDocumentAssociationItems[${documentAssociationItemIndex}].title"
					name="unitManagerReviewDocumentAssociationItems[${documentAssociationItemIndex}].title"
				value="${unitManagerReviewDocumentAssociationItems[documentAssociationItemIndex].title}" />
			<form:errors path="unitManagerReviewDocumentAssociationItems[${documentAssociationItemIndex}].title" cssClass="error"/>
		</span>
		
		<span class="fieldGroup">
			<label for="unitManagerReviewDocumentAssociationItems[${documentAssociationItemIndex}].fileDate" class="fieldLabel">
				<fmt:message key="fileDateLabel"/>
			</label>
			<fmt:formatDate var="unitManagerReviewDocumentAssociationDate" value="${unitManagerReviewDocumentAssociationItem.fileDate}" pattern="MM/dd/yyyy"/>
			<input type="text" value="${unitManagerReviewDocumentAssociationDate}" id="unitManagerReviewDocumentAssociationItems[${documentAssociationItemIndex}].fileDate"
					name="unitManagerReviewDocumentAssociationItems[${documentAssociationItemIndex}].fileDate" class="date" />
			<form:errors path="unitManagerReviewDocumentAssociationItems[${documentAssociationItemIndex}].fileDate" cssClass="error"/>
		</span>
		
		<span class="fieldGroup">
			<a id="createDocumentTagItemLink${documentAssociationItemIndex}" class="createLink addLink">
				<span class="visibleLinkLabel">
					<fmt:message key="addTagLink"/>
				</span>
			</a>
		</span>
		
		<span id="documentTagItems${documentAssociationItemIndex}">
			<c:forEach var="documentTagItem" items="${unitManagerReviewDocumentAssociationItems[documentAssociationItemIndex].documentTagItems}" varStatus="status">
				<c:set var="documentTagItem" value="${documentTagItem}" scope="request"/>
				<c:set var="documentTagItemIndex" value="${status.index}" scope="request"/>
				<c:if test="${not empty documentTagItem.itemOperation}">
					<jsp:include page="documentAssociationTagItemContent.jsp"/>
				</c:if>
			</c:forEach>
		</span>
		
		<span class="fieldGroup">
			<label for="unitManagerReviewDocumentAssociationItems[${documentAssociationItemIndex}].data" class="fieldLabel">
				<fmt:message key="documentLabel"/>
			</label>
			<c:choose>
				<c:when test="${not empty unitManagerReviewDocumentAssociationItem.document}">
					<c:set var="filename" value = "${unitManagerReviewDocumentAssociationItems[documentAssociationItemIndex].document.filename}"/>
					<fmt:formatDate value="${unitManagerReviewDocumentAssociationItems[documentAssociationItemIndex].document.date}"
							pattern="MM/dd/yyyy" var="documentDate"/>
					<a id="unitManagerReviewDocumentAssociationDownloadLink${documentAssociationItemIndex}"
		 					href="${pageContext.request.contextPath}/unitManagerReview/retrieveFile.html?document=${unitManagerReviewDocumentAssociationItems[documentAssociationItemIndex].document.id}"
							class="downloadLink">
						<fmt:message key="titleExtensionFormat" bundle="${documentBundle}">
							<fmt:param value="${filename}"/><fmt:param value="${documentDate}"/>
						</fmt:message>
					</a>
					<input type="hidden" name="unitManagerReviewDocumentAssociationItems[${documentAssociationItemIndex}].data"
						id="unitManagerReviewDocumentAssociationItems[${documentAssociationItemIndex}].data"
						value="${unitManagerReviewDocumentAssociationItems[documentAssociationItemIndex].data}"/>
					<input type="hidden" name="unitManagerReviewDocumentAssociationItems[${documentAssociationItemIndex}].document"
						id="unitManagerReviewDocumentAssociationItems[${documentAssociationItemIndex}].document"
						value="${unitManagerReviewDocumentAssociationItems[documentAssociationItemIndex].document.id}"/>
				</c:when>
				<c:otherwise>
						<input id="unitManagerReviewDocumentAssociationItems[${documentAssociationItemIndex}].data" type="file"
								name="unitManagerReviewDocumentAssociationItems[${documentAssociationItemIndex}].data" />
				</c:otherwise>
			</c:choose>
			
			<input type="hidden" name="unitManagerReviewDocumentAssociationItems[${documentAssociationItemIndex}].fileExtension"
					id="unitManagerReviewDocumentAssociationItems[${documentAssociationItemIndex}].fileExtension"
					value="${unitManagerReviewDocumentAssociationItems[documentAssociationItemIndex].fileExtension}"/>
			<form:errors path="unitManagerReviewDocumentAssociationItems[${documentAssociationItemIndex}].data" cssClass="error"/>
		</span>
	</span>
</fmt:bundle>