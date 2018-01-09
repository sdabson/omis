<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.document.msgs.document" var="documentBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.victimSectionSummary">
	<span id="victimSectionSummaryDocumentAssociationItem${victimSectionSummaryDocumentAssociationItemIndex}" class="summaryDocumentItem">
		<a class="removeLink" id="removeVictimSectionSummaryDocumentAssociationLink${victimSectionSummaryDocumentAssociationItemIndex}"
		href="${pageContext.request.contextPath}/presentenceInvestigation/victimSummary/removeVictimSectionSummaryDocumentAssociation.html?victimSectionSummary=${victimSectionSummary.id}">
			<fmt:message key="removeLink" bundle="${commonBundle}"/>
		</a>
		<input type="hidden" id="victimSectionSummaryDocumentAssociationId${victimSectionSummaryDocumentAssociationItemIndex}"
				name="victimSectionSummaryDocumentAssociationItems[${victimSectionSummaryDocumentAssociationItemIndex}].victimSectionSummaryDocumentAssociation"
				value="${victimSectionSummaryDocumentAssociationItem.victimSectionSummaryDocumentAssociation.id}"/>
		<form:errors path="victimSectionSummaryDocumentAssociationItems[${victimSectionSummaryDocumentAssociationItemIndex}].victimSectionSummaryDocumentAssociation" cssClass="error"/>
		<input type="hidden" id="victimSectionSummaryDocumentAssociationOperation${victimSectionSummaryDocumentAssociationItemIndex}"
				name="victimSectionSummaryDocumentAssociationItems[${victimSectionSummaryDocumentAssociationItemIndex}].itemOperation" value="${victimSectionSummaryDocumentAssociationItem.itemOperation}"/>
		<form:errors path="victimSectionSummaryDocumentAssociationItems[${victimSectionSummaryDocumentAssociationItemIndex}].itemOperation" cssClass="error"/>
		
		<span class="fieldGroup">
			<label for="victimSectionSummaryDocumentAssociationItems[${victimSectionSummaryDocumentAssociationItemIndex}].title" class="fieldLabel">
				<fmt:message key="documentTitleLabel"/>
			</label>
			<input type="text" id="victimSectionSummaryDocumentAssociationItems[${victimSectionSummaryDocumentAssociationItemIndex}].title"
					name="victimSectionSummaryDocumentAssociationItems[${victimSectionSummaryDocumentAssociationItemIndex}].title"
				value="${victimSectionSummaryDocumentAssociationItems[victimSectionSummaryDocumentAssociationItemIndex].title}" />
			<form:errors path="victimSectionSummaryDocumentAssociationItems[${victimSectionSummaryDocumentAssociationItemIndex}].title" cssClass="error"/>
		</span>
		
		<span class="fieldGroup">
			<label for="victimSectionSummaryDocumentAssociationItems[${victimSectionSummaryDocumentAssociationItemIndex}].fileDate" class="fieldLabel">
				<fmt:message key="fileDateLabel"/>
			</label>
			<fmt:formatDate var="victimSectionSummaryDocumentAssociationDate" value="${victimSectionSummaryDocumentAssociationItem.fileDate}" pattern="MM/dd/yyyy"/>
			<input type="text" value="${victimSectionSummaryDocumentAssociationDate}" id="victimSectionSummaryDocumentAssociationItems[${victimSectionSummaryDocumentAssociationItemIndex}].fileDate"
					name="victimSectionSummaryDocumentAssociationItems[${victimSectionSummaryDocumentAssociationItemIndex}].fileDate" class="date" />
			<form:errors path="victimSectionSummaryDocumentAssociationItems[${victimSectionSummaryDocumentAssociationItemIndex}].fileDate" cssClass="error"/>
		</span>
		
		<span class="fieldGroup">
			<a id="createDocumentTagItemLink${victimSectionSummaryDocumentAssociationItemIndex}" class="createLink addLink">
				<span class="visibleLinkLabel">
					<fmt:message key="addTagLink"/>
				</span>
			</a>
		</span>
		
		<span id="documentTagItems${victimSectionSummaryDocumentAssociationItemIndex}">
			<c:forEach var="documentTagItem" items="${victimSectionSummaryDocumentAssociationItems[victimSectionSummaryDocumentAssociationItemIndex].documentTagItems}" varStatus="status">
				<c:set var="documentTagItem" value="${documentTagItem}" scope="request"/>
				<c:set var="documentTagItemIndex" value="${status.index}" scope="request"/>
				<c:if test="${not empty documentTagItem.itemOperation}">
					<jsp:include page="victimSectionSummaryDocumentAssociationTagItemContent.jsp"/>
				</c:if>
			</c:forEach>
		</span>
		
		
		<span class="fieldGroup">
			<label for="victimSectionSummaryDocumentAssociationItems[${victimSectionSummaryDocumentAssociationItemIndex}].data" class="fieldLabel">
				<fmt:message key="documentLabel"/>
			</label>
			
			<c:choose>
				<c:when test="${not empty victimSectionSummaryDocumentAssociationItem.document}">
					<c:set var="filename" value = "${victimSectionSummaryDocumentAssociationItems[victimSectionSummaryDocumentAssociationItemIndex].document.filename}"/>
					<fmt:formatDate value="${victimSectionSummaryDocumentAssociationItems[victimSectionSummaryDocumentAssociationItemIndex].document.date}"
							pattern="MM/dd/yyyy" var="documentDate"/>
					<a id="victimSectionSummaryDocumentAssociationDownloadLink${victimSectionSummaryDocumentAssociationItemIndex}"
		 					href="${pageContext.request.contextPath}/presentenceInvestigation/victimSummary/retrieveFile.html?document=${victimSectionSummaryDocumentAssociationItems[victimSectionSummaryDocumentAssociationItemIndex].document.id}"
							class="downloadLink">
						<fmt:message key="titleExtensionFormat" bundle="${documentBundle}">
							<fmt:param value="${filename}"/><fmt:param value="${documentDate}"/>
						</fmt:message>
					</a>
					<input type="hidden" name="victimSectionSummaryDocumentAssociationItems[${victimSectionSummaryDocumentAssociationItemIndex}].data"
						id="victimSectionSummaryDocumentAssociationItems[${victimSectionSummaryDocumentAssociationItemIndex}].data"
						value="${victimSectionSummaryDocumentAssociationItems[victimSectionSummaryDocumentAssociationItemIndex].data}"/>
					<input type="hidden" name="victimSectionSummaryDocumentAssociationItems[${victimSectionSummaryDocumentAssociationItemIndex}].document"
						id="victimSectionSummaryDocumentAssociationItems[${victimSectionSummaryDocumentAssociationItemIndex}].document"
						value="${victimSectionSummaryDocumentAssociationItems[victimSectionSummaryDocumentAssociationItemIndex].document.id}"/>
				</c:when>
				<c:otherwise>
						<input id="victimSectionSummaryDocumentAssociationItems[${victimSectionSummaryDocumentAssociationItemIndex}].data" type="file"
								name="victimSectionSummaryDocumentAssociationItems[${victimSectionSummaryDocumentAssociationItemIndex}].data" />
				</c:otherwise>
			</c:choose>
			
			<input type="hidden" name="victimSectionSummaryDocumentAssociationItems[${victimSectionSummaryDocumentAssociationItemIndex}].fileExtension"
					id="victimSectionSummaryDocumentAssociationItems[${victimSectionSummaryDocumentAssociationItemIndex}].fileExtension"
					value="${victimSectionSummaryDocumentAssociationItems[victimSectionSummaryDocumentAssociationItemIndex].fileExtension}"/>
			<form:errors path="victimSectionSummaryDocumentAssociationItems[${victimSectionSummaryDocumentAssociationItemIndex}].data" cssClass="error"/>
		</span>
		
		
		
		
		
	</span>
</fmt:bundle>