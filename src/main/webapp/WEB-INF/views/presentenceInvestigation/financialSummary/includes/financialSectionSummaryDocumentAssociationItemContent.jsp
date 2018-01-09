<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.document.msgs.document" var="documentBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.financialSectionSummary">
	<span id="financialSectionSummaryDocumentAssociationItem${financialSectionSummaryDocumentAssociationItemIndex}" class="summaryDocumentItem">
		<a class="removeLink" id="removeFinancialSectionSummaryDocumentAssociationLink${financialSectionSummaryDocumentAssociationItemIndex}"
		href="${pageContext.request.contextPath}/presentenceInvestigation/financialSummary/removeFinancialSectionSummaryDocumentAssociation.html?financialSectionSummary=${financialSectionSummary.id}">
			<fmt:message key="removeLink" bundle="${commonBundle}"/>
		</a>
		<input type="hidden" id="financialSectionSummaryDocumentAssociationId${financialSectionSummaryDocumentAssociationItemIndex}"
				name="financialSectionSummaryDocumentAssociationItems[${financialSectionSummaryDocumentAssociationItemIndex}].financialSectionSummaryDocumentAssociation"
				value="${financialSectionSummaryDocumentAssociationItem.financialSectionSummaryDocumentAssociation.id}"/>
		<form:errors path="financialSectionSummaryDocumentAssociationItems[${financialSectionSummaryDocumentAssociationItemIndex}].financialSectionSummaryDocumentAssociation" cssClass="error"/>
		<input type="hidden" id="financialSectionSummaryDocumentAssociationOperation${financialSectionSummaryDocumentAssociationItemIndex}"
				name="financialSectionSummaryDocumentAssociationItems[${financialSectionSummaryDocumentAssociationItemIndex}].itemOperation" value="${financialSectionSummaryDocumentAssociationItem.itemOperation}"/>
		<form:errors path="financialSectionSummaryDocumentAssociationItems[${financialSectionSummaryDocumentAssociationItemIndex}].itemOperation" cssClass="error"/>
		
		<span class="fieldGroup">
			<label for="financialSectionSummaryDocumentAssociationItems[${financialSectionSummaryDocumentAssociationItemIndex}].title" class="fieldLabel">
				<fmt:message key="documentTitleLabel"/>
			</label>
			<input type="text" id="financialSectionSummaryDocumentAssociationItems[${financialSectionSummaryDocumentAssociationItemIndex}].title"
					name="financialSectionSummaryDocumentAssociationItems[${financialSectionSummaryDocumentAssociationItemIndex}].title"
				value="${financialSectionSummaryDocumentAssociationItems[financialSectionSummaryDocumentAssociationItemIndex].title}" />
			<form:errors path="financialSectionSummaryDocumentAssociationItems[${financialSectionSummaryDocumentAssociationItemIndex}].title" cssClass="error"/>
		</span>
		
		<span class="fieldGroup">
			<label for="financialSectionSummaryDocumentAssociationItems[${financialSectionSummaryDocumentAssociationItemIndex}].fileDate" class="fieldLabel">
				<fmt:message key="fileDateLabel"/>
			</label>
			<fmt:formatDate var="financialSectionSummaryDocumentAssociationDate" value="${financialSectionSummaryDocumentAssociationItem.fileDate}" pattern="MM/dd/yyyy"/>
			<input type="text" value="${financialSectionSummaryDocumentAssociationDate}" id="financialSectionSummaryDocumentAssociationItems[${financialSectionSummaryDocumentAssociationItemIndex}].fileDate"
					name="financialSectionSummaryDocumentAssociationItems[${financialSectionSummaryDocumentAssociationItemIndex}].fileDate" class="date" />
			<form:errors path="financialSectionSummaryDocumentAssociationItems[${financialSectionSummaryDocumentAssociationItemIndex}].fileDate" cssClass="error"/>
		</span>
		
		<span class="fieldGroup">
			<a id="createDocumentTagItemLink${financialSectionSummaryDocumentAssociationItemIndex}" class="createLink addLink">
				<span class="visibleLinkLabel">
					<fmt:message key="addTagLink"/>
				</span>
			</a>
		</span>
		
		<span id="documentTagItems${financialSectionSummaryDocumentAssociationItemIndex}">
			<c:forEach var="documentTagItem" items="${financialSectionSummaryDocumentAssociationItems[financialSectionSummaryDocumentAssociationItemIndex].documentTagItems}" varStatus="status">
				<c:set var="documentTagItem" value="${documentTagItem}" scope="request"/>
				<c:set var="documentTagItemIndex" value="${status.index}" scope="request"/>
				<c:if test="${not empty documentTagItem.itemOperation}">
					<jsp:include page="financialSectionSummaryDocumentAssociationTagItemContent.jsp"/>
				</c:if>
			</c:forEach>
		</span>
		
		
		<span class="fieldGroup">
			<label for="financialSectionSummaryDocumentAssociationItems[${financialSectionSummaryDocumentAssociationItemIndex}].data" class="fieldLabel">
				<fmt:message key="documentLabel"/>
			</label>
			
			<c:choose>
				<c:when test="${not empty financialSectionSummaryDocumentAssociationItem.document}">
					<c:set var="filename" value = "${financialSectionSummaryDocumentAssociationItems[financialSectionSummaryDocumentAssociationItemIndex].document.filename}"/>
					<fmt:formatDate value="${financialSectionSummaryDocumentAssociationItems[financialSectionSummaryDocumentAssociationItemIndex].document.date}"
							pattern="MM/dd/yyyy" var="documentDate"/>
					<a id="financialSectionSummaryDocumentAssociationDownloadLink${financialSectionSummaryDocumentAssociationItemIndex}"
		 					href="${pageContext.request.contextPath}/presentenceInvestigation/financialSummary/retrieveFile.html?document=${financialSectionSummaryDocumentAssociationItems[financialSectionSummaryDocumentAssociationItemIndex].document.id}"
							class="downloadLink">
						<fmt:message key="titleExtensionFormat" bundle="${documentBundle}">
							<fmt:param value="${filename}"/><fmt:param value="${documentDate}"/>
						</fmt:message>
					</a>
					<input type="hidden" name="financialSectionSummaryDocumentAssociationItems[${financialSectionSummaryDocumentAssociationItemIndex}].data"
						id="financialSectionSummaryDocumentAssociationItems[${financialSectionSummaryDocumentAssociationItemIndex}].data"
						value="${financialSectionSummaryDocumentAssociationItems[financialSectionSummaryDocumentAssociationItemIndex].data}"/>
					<input type="hidden" name="financialSectionSummaryDocumentAssociationItems[${financialSectionSummaryDocumentAssociationItemIndex}].document"
						id="financialSectionSummaryDocumentAssociationItems[${financialSectionSummaryDocumentAssociationItemIndex}].document"
						value="${financialSectionSummaryDocumentAssociationItems[financialSectionSummaryDocumentAssociationItemIndex].document.id}"/>
				</c:when>
				<c:otherwise>
						<input id="financialSectionSummaryDocumentAssociationItems[${financialSectionSummaryDocumentAssociationItemIndex}].data" type="file"
								name="financialSectionSummaryDocumentAssociationItems[${financialSectionSummaryDocumentAssociationItemIndex}].data" />
				</c:otherwise>
			</c:choose>
			
			<input type="hidden" name="financialSectionSummaryDocumentAssociationItems[${financialSectionSummaryDocumentAssociationItemIndex}].fileExtension"
					id="financialSectionSummaryDocumentAssociationItems[${financialSectionSummaryDocumentAssociationItemIndex}].fileExtension"
					value="${financialSectionSummaryDocumentAssociationItems[financialSectionSummaryDocumentAssociationItemIndex].fileExtension}"/>
			<form:errors path="financialSectionSummaryDocumentAssociationItems[${financialSectionSummaryDocumentAssociationItemIndex}].data" cssClass="error"/>
		</span>	
	</span>
</fmt:bundle>