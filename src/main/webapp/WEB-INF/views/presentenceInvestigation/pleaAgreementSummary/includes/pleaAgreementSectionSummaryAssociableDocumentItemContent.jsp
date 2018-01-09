<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.document.msgs.document" var="documentBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.pleaAgreementSectionSummary">
	<span id="pleaAgreementSectionSummaryAssociableDocumentItem${pleaAgreementSectionSummaryAssociableDocumentItemIndex}" class="summaryDocumentItem">
		<a class="removeLink" id="removePleaAgreementSectionSummaryAssociableDocumentLink${pleaAgreementSectionSummaryAssociableDocumentItemIndex}"
		href="${pageContext.request.contextPath}/presentenceInvestigation/pleaAgreementSummary/removePleaAgreementSectionSummaryAssociableDocument.html?pleaAgreementSectionSummary=${pleaAgreementSectionSummary.id}">
			<fmt:message key="removeLink" bundle="${commonBundle}"/>
		</a>
		<input type="hidden" id="pleaAgreementSectionSummaryAssociableDocumentId${pleaAgreementSectionSummaryAssociableDocumentItemIndex}"
				name="pleaAgreementSectionSummaryAssociableDocumentItems[${pleaAgreementSectionSummaryAssociableDocumentItemIndex}].pleaAgreementSectionSummaryAssociableDocument"
				value="${pleaAgreementSectionSummaryAssociableDocumentItem.pleaAgreementSectionSummaryAssociableDocument.id}"/>
		<form:errors path="pleaAgreementSectionSummaryAssociableDocumentItems[${pleaAgreementSectionSummaryAssociableDocumentItemIndex}].pleaAgreementSectionSummaryAssociableDocument" cssClass="error"/>
		<input type="hidden" id="pleaAgreementSectionSummaryAssociableDocumentOperation${pleaAgreementSectionSummaryAssociableDocumentItemIndex}"
				name="pleaAgreementSectionSummaryAssociableDocumentItems[${pleaAgreementSectionSummaryAssociableDocumentItemIndex}].itemOperation" value="${pleaAgreementSectionSummaryAssociableDocumentItem.itemOperation}"/>
		<form:errors path="pleaAgreementSectionSummaryAssociableDocumentItems[${pleaAgreementSectionSummaryAssociableDocumentItemIndex}].itemOperation" cssClass="error"/>
		
		<span class="fieldGroup">
			<label for="pleaAgreementSectionSummaryAssociableDocumentItems[${pleaAgreementSectionSummaryAssociableDocumentItemIndex}].title" class="fieldLabel">
				<fmt:message key="documentTitleLabel"/>
			</label>
			<input type="text" id="pleaAgreementSectionSummaryAssociableDocumentItems[${pleaAgreementSectionSummaryAssociableDocumentItemIndex}].title"
					name="pleaAgreementSectionSummaryAssociableDocumentItems[${pleaAgreementSectionSummaryAssociableDocumentItemIndex}].title"
				value="${pleaAgreementSectionSummaryAssociableDocumentItems[pleaAgreementSectionSummaryAssociableDocumentItemIndex].title}" />
			<form:errors path="pleaAgreementSectionSummaryAssociableDocumentItems[${pleaAgreementSectionSummaryAssociableDocumentItemIndex}].title" cssClass="error"/>
		</span>
		
		<span class="fieldGroup">
			<label for="pleaAgreementSectionSummaryAssociableDocumentItems[${pleaAgreementSectionSummaryAssociableDocumentItemIndex}].fileDate" class="fieldLabel">
				<fmt:message key="fileDateLabel"/>
			</label>
			<fmt:formatDate var="pleaAgreementSectionSummaryAssociableDocumentDate" value="${pleaAgreementSectionSummaryAssociableDocumentItem.fileDate}" pattern="MM/dd/yyyy"/>
			<input type="text" value="${pleaAgreementSectionSummaryAssociableDocumentDate}" id="pleaAgreementSectionSummaryAssociableDocumentItems[${pleaAgreementSectionSummaryAssociableDocumentItemIndex}].fileDate"
					name="pleaAgreementSectionSummaryAssociableDocumentItems[${pleaAgreementSectionSummaryAssociableDocumentItemIndex}].fileDate" class="date" />
			<form:errors path="pleaAgreementSectionSummaryAssociableDocumentItems[${pleaAgreementSectionSummaryAssociableDocumentItemIndex}].fileDate" cssClass="error"/>
		</span>
		
		<span class="fieldGroup">
			<a id="createDocumentTagItemLink${pleaAgreementSectionSummaryAssociableDocumentItemIndex}" class="createLink addLink">
				<span class="visibleLinkLabel">
					<fmt:message key="addTagLink"/>
				</span>
			</a>
		</span>
		
		<span id="documentTagItems${pleaAgreementSectionSummaryAssociableDocumentItemIndex}">
			<c:forEach var="documentTagItem" items="${pleaAgreementSectionSummaryAssociableDocumentItems[pleaAgreementSectionSummaryAssociableDocumentItemIndex].documentTagItems}" varStatus="status">
				<c:set var="documentTagItem" value="${documentTagItem}" scope="request"/>
				<c:set var="documentTagItemIndex" value="${status.index}" scope="request"/>
				<c:if test="${not empty documentTagItem.itemOperation}">
					<jsp:include page="pleaAgreementSectionSummaryAssociableDocumentTagItemContent.jsp"/>
				</c:if>
			</c:forEach>
		</span>
		
		
		<span class="fieldGroup">
			<label for="pleaAgreementSectionSummaryAssociableDocumentItems[${pleaAgreementSectionSummaryAssociableDocumentItemIndex}].data" class="fieldLabel">
				<fmt:message key="documentLabel"/>
			</label>
			
			<c:choose>
				<c:when test="${not empty pleaAgreementSectionSummaryAssociableDocumentItem.document}">
					<c:set var="filename" value = "${pleaAgreementSectionSummaryAssociableDocumentItems[pleaAgreementSectionSummaryAssociableDocumentItemIndex].document.filename}"/>
					<fmt:formatDate value="${pleaAgreementSectionSummaryAssociableDocumentItems[pleaAgreementSectionSummaryAssociableDocumentItemIndex].document.date}"
							pattern="MM/dd/yyyy" var="documentDate"/>
					<a id="pleaAgreementSectionSummaryAssociableDocumentDownloadLink${pleaAgreementSectionSummaryAssociableDocumentItemIndex}"
		 					href="${pageContext.request.contextPath}/presentenceInvestigation/pleaAgreementSummary/retrieveFile.html?document=${pleaAgreementSectionSummaryAssociableDocumentItems[pleaAgreementSectionSummaryAssociableDocumentItemIndex].document.id}"
							class="downloadLink">
						<fmt:message key="titleExtensionFormat" bundle="${documentBundle}">
							<fmt:param value="${filename}"/><fmt:param value="${documentDate}"/>
						</fmt:message>
					</a>
					<input type="hidden" name="pleaAgreementSectionSummaryAssociableDocumentItems[${pleaAgreementSectionSummaryAssociableDocumentItemIndex}].data"
						id="pleaAgreementSectionSummaryAssociableDocumentItems[${pleaAgreementSectionSummaryAssociableDocumentItemIndex}].data"
						value="${pleaAgreementSectionSummaryAssociableDocumentItems[pleaAgreementSectionSummaryAssociableDocumentItemIndex].data}"/>
					<input type="hidden" name="pleaAgreementSectionSummaryAssociableDocumentItems[${pleaAgreementSectionSummaryAssociableDocumentItemIndex}].document"
						id="pleaAgreementSectionSummaryAssociableDocumentItems[${pleaAgreementSectionSummaryAssociableDocumentItemIndex}].document"
						value="${pleaAgreementSectionSummaryAssociableDocumentItems[pleaAgreementSectionSummaryAssociableDocumentItemIndex].document.id}"/>
				</c:when>
				<c:otherwise>
						<input id="pleaAgreementSectionSummaryAssociableDocumentItems[${pleaAgreementSectionSummaryAssociableDocumentItemIndex}].data" type="file"
								name="pleaAgreementSectionSummaryAssociableDocumentItems[${pleaAgreementSectionSummaryAssociableDocumentItemIndex}].data" />
				</c:otherwise>
			</c:choose>
			
			<input type="hidden" name="pleaAgreementSectionSummaryAssociableDocumentItems[${pleaAgreementSectionSummaryAssociableDocumentItemIndex}].fileExtension"
					id="pleaAgreementSectionSummaryAssociableDocumentItems[${pleaAgreementSectionSummaryAssociableDocumentItemIndex}].fileExtension"
					value="${pleaAgreementSectionSummaryAssociableDocumentItems[pleaAgreementSectionSummaryAssociableDocumentItemIndex].fileExtension}"/>
			<form:errors path="pleaAgreementSectionSummaryAssociableDocumentItems[${pleaAgreementSectionSummaryAssociableDocumentItemIndex}].data" cssClass="error"/>
		</span>
		
		
		
		
		
	</span>
</fmt:bundle>