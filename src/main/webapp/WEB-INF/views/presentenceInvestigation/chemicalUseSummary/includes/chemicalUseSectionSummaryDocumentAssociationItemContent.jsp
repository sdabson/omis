<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.document.msgs.document" var="documentBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.chemicalUseSectionSummary">
	<span id="chemicalUseSectionSummaryDocumentAssociationItem${chemicalUseSectionSummaryDocumentAssociationItemIndex}" class="summaryDocumentItem">
		<a class="removeLink" id="removeChemicalUseSectionSummaryDocumentAssociationLink${chemicalUseSectionSummaryDocumentAssociationItemIndex}"
		href="${pageContext.request.contextPath}/presentenceInvestigation/chemicalUseSummary/removeChemicalUseSectionSummaryDocumentAssociation.html?chemicalUseSectionSummary=${chemicalUseSectionSummary.id}">
			<fmt:message key="removeLink" bundle="${commonBundle}"/>
		</a>
		<input type="hidden" id="chemicalUseSectionSummaryDocumentAssociationId${chemicalUseSectionSummaryDocumentAssociationItemIndex}"
				name="chemicalUseSectionSummaryDocumentAssociationItems[${chemicalUseSectionSummaryDocumentAssociationItemIndex}].chemicalUseSectionSummaryDocumentAssociation"
				value="${chemicalUseSectionSummaryDocumentAssociationItem.chemicalUseSectionSummaryDocumentAssociation.id}"/>
		<form:errors path="chemicalUseSectionSummaryDocumentAssociationItems[${chemicalUseSectionSummaryDocumentAssociationItemIndex}].chemicalUseSectionSummaryDocumentAssociation" cssClass="error"/>
		<input type="hidden" id="chemicalUseSectionSummaryDocumentAssociationOperation${chemicalUseSectionSummaryDocumentAssociationItemIndex}"
				name="chemicalUseSectionSummaryDocumentAssociationItems[${chemicalUseSectionSummaryDocumentAssociationItemIndex}].itemOperation" value="${chemicalUseSectionSummaryDocumentAssociationItem.itemOperation}"/>
		<form:errors path="chemicalUseSectionSummaryDocumentAssociationItems[${chemicalUseSectionSummaryDocumentAssociationItemIndex}].itemOperation" cssClass="error"/>
		
		<span class="fieldGroup">
			<label for="chemicalUseSectionSummaryDocumentAssociationItems[${chemicalUseSectionSummaryDocumentAssociationItemIndex}].title" class="fieldLabel">
				<fmt:message key="documentTitleLabel"/>
			</label>
			<input type="text" id="chemicalUseSectionSummaryDocumentAssociationItems[${chemicalUseSectionSummaryDocumentAssociationItemIndex}].title"
					name="chemicalUseSectionSummaryDocumentAssociationItems[${chemicalUseSectionSummaryDocumentAssociationItemIndex}].title"
				value="${chemicalUseSectionSummaryDocumentAssociationItems[chemicalUseSectionSummaryDocumentAssociationItemIndex].title}" />
			<form:errors path="chemicalUseSectionSummaryDocumentAssociationItems[${chemicalUseSectionSummaryDocumentAssociationItemIndex}].title" cssClass="error"/>
		</span>
		
		<span class="fieldGroup">
			<label for="chemicalUseSectionSummaryDocumentAssociationItems[${chemicalUseSectionSummaryDocumentAssociationItemIndex}].fileDate" class="fieldLabel">
				<fmt:message key="fileDateLabel"/>
			</label>
			<fmt:formatDate var="chemicalUseSectionSummaryDocumentAssociationDate" value="${chemicalUseSectionSummaryDocumentAssociationItem.fileDate}" pattern="MM/dd/yyyy"/>
			<input type="text" value="${chemicalUseSectionSummaryDocumentAssociationDate}" id="chemicalUseSectionSummaryDocumentAssociationItems[${chemicalUseSectionSummaryDocumentAssociationItemIndex}].fileDate"
					name="chemicalUseSectionSummaryDocumentAssociationItems[${chemicalUseSectionSummaryDocumentAssociationItemIndex}].fileDate" class="date" />
			<form:errors path="chemicalUseSectionSummaryDocumentAssociationItems[${chemicalUseSectionSummaryDocumentAssociationItemIndex}].fileDate" cssClass="error"/>
		</span>
		
		<span class="fieldGroup">
			<a id="createDocumentTagItemLink${chemicalUseSectionSummaryDocumentAssociationItemIndex}" class="createLink addLink">
				<span class="visibleLinkLabel">
					<fmt:message key="addTagLink"/>
				</span>
			</a>
		</span>
		
		<span id="documentTagItems${chemicalUseSectionSummaryDocumentAssociationItemIndex}">
			<c:forEach var="documentTagItem" items="${chemicalUseSectionSummaryDocumentAssociationItems[chemicalUseSectionSummaryDocumentAssociationItemIndex].documentTagItems}" varStatus="status">
				<c:set var="documentTagItem" value="${documentTagItem}" scope="request"/>
				<c:set var="documentTagItemIndex" value="${status.index}" scope="request"/>
				<c:if test="${not empty documentTagItem.itemOperation}">
					<jsp:include page="chemicalUseSectionSummaryDocumentAssociationTagItemContent.jsp"/>
				</c:if>
			</c:forEach>
		</span>
		
		
		<span class="fieldGroup">
			<label for="chemicalUseSectionSummaryDocumentAssociationItems[${chemicalUseSectionSummaryDocumentAssociationItemIndex}].data" class="fieldLabel">
				<fmt:message key="documentLabel"/>
			</label>
			
			<c:choose>
				<c:when test="${not empty chemicalUseSectionSummaryDocumentAssociationItem.document}">
					<c:set var="filename" value = "${chemicalUseSectionSummaryDocumentAssociationItems[chemicalUseSectionSummaryDocumentAssociationItemIndex].document.filename}"/>
					<fmt:formatDate value="${chemicalUseSectionSummaryDocumentAssociationItems[chemicalUseSectionSummaryDocumentAssociationItemIndex].document.date}"
							pattern="MM/dd/yyyy" var="documentDate"/>
					<a id="chemicalUseSectionSummaryDocumentAssociationDownloadLink${chemicalUseSectionSummaryDocumentAssociationItemIndex}"
		 					href="${pageContext.request.contextPath}/presentenceInvestigation/chemicalUseSummary/retrieveFile.html?document=${chemicalUseSectionSummaryDocumentAssociationItems[chemicalUseSectionSummaryDocumentAssociationItemIndex].document.id}"
							class="downloadLink">
						<fmt:message key="titleExtensionFormat" bundle="${documentBundle}">
							<fmt:param value="${filename}"/><fmt:param value="${documentDate}"/>
						</fmt:message>
					</a>
					<input type="hidden" name="chemicalUseSectionSummaryDocumentAssociationItems[${chemicalUseSectionSummaryDocumentAssociationItemIndex}].data"
						id="chemicalUseSectionSummaryDocumentAssociationItems[${chemicalUseSectionSummaryDocumentAssociationItemIndex}].data"
						value="${chemicalUseSectionSummaryDocumentAssociationItems[chemicalUseSectionSummaryDocumentAssociationItemIndex].data}"/>
					<input type="hidden" name="chemicalUseSectionSummaryDocumentAssociationItems[${chemicalUseSectionSummaryDocumentAssociationItemIndex}].document"
						id="chemicalUseSectionSummaryDocumentAssociationItems[${chemicalUseSectionSummaryDocumentAssociationItemIndex}].document"
						value="${chemicalUseSectionSummaryDocumentAssociationItems[chemicalUseSectionSummaryDocumentAssociationItemIndex].document.id}"/>
				</c:when>
				<c:otherwise>
						<input id="chemicalUseSectionSummaryDocumentAssociationItems[${chemicalUseSectionSummaryDocumentAssociationItemIndex}].data" type="file"
								name="chemicalUseSectionSummaryDocumentAssociationItems[${chemicalUseSectionSummaryDocumentAssociationItemIndex}].data" />
				</c:otherwise>
			</c:choose>
			
			<input type="hidden" name="chemicalUseSectionSummaryDocumentAssociationItems[${chemicalUseSectionSummaryDocumentAssociationItemIndex}].fileExtension"
					id="chemicalUseSectionSummaryDocumentAssociationItems[${chemicalUseSectionSummaryDocumentAssociationItemIndex}].fileExtension"
					value="${chemicalUseSectionSummaryDocumentAssociationItems[chemicalUseSectionSummaryDocumentAssociationItemIndex].fileExtension}"/>
			<form:errors path="chemicalUseSectionSummaryDocumentAssociationItems[${chemicalUseSectionSummaryDocumentAssociationItemIndex}].data" cssClass="error"/>
		</span>
		
		
		
		
		
	</span>
</fmt:bundle>