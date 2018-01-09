<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.document.msgs.document" var="documentBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.psychologicalSectionSummary">
	<span id="psychologicalSectionSummaryDocumentItem${psychologicalSectionSummaryDocumentItemIndex}" class="summaryDocumentItem">
		<a class="removeLink" id="removePsychologicalSectionSummaryDocumentLink${psychologicalSectionSummaryDocumentItemIndex}"
		href="${pageContext.request.contextPath}/presentenceInvestigation/psychologicalSummary/removePsychologicalSectionSummaryDocument.html?psychologicalSectionSummary=${psychologicalSectionSummary.id}">
			<fmt:message key="removeLink" bundle="${commonBundle}"/>
		</a>
		<input type="hidden" id="psychologicalSectionSummaryDocumentId${psychologicalSectionSummaryDocumentItemIndex}"
				name="psychologicalSectionSummaryDocumentItems[${psychologicalSectionSummaryDocumentItemIndex}].psychologicalSectionSummaryDocument"
				value="${psychologicalSectionSummaryDocumentItem.psychologicalSectionSummaryDocument.id}"/>
		<form:errors path="psychologicalSectionSummaryDocumentItems[${psychologicalSectionSummaryDocumentItemIndex}].psychologicalSectionSummaryDocument" cssClass="error"/>
		<input type="hidden" id="psychologicalSectionSummaryDocumentOperation${psychologicalSectionSummaryDocumentItemIndex}"
				name="psychologicalSectionSummaryDocumentItems[${psychologicalSectionSummaryDocumentItemIndex}].itemOperation" value="${psychologicalSectionSummaryDocumentItem.itemOperation}"/>
		<form:errors path="psychologicalSectionSummaryDocumentItems[${psychologicalSectionSummaryDocumentItemIndex}].itemOperation" cssClass="error"/>
		
		
		
		
		<span class="fieldGroup">
			<label for="psychologicalSectionSummaryDocumentItems[${psychologicalSectionSummaryDocumentItemIndex}].title" class="fieldLabel">
				<fmt:message key="documentTitleLabel"/>
			</label>
			<input type="text" id="psychologicalSectionSummaryDocumentItems[${psychologicalSectionSummaryDocumentItemIndex}].title"
					name="psychologicalSectionSummaryDocumentItems[${psychologicalSectionSummaryDocumentItemIndex}].title"
				value="${psychologicalSectionSummaryDocumentItems[psychologicalSectionSummaryDocumentItemIndex].title}" />
			<form:errors path="psychologicalSectionSummaryDocumentItems[${psychologicalSectionSummaryDocumentItemIndex}].title" cssClass="error"/>
		</span>
		
		<span class="fieldGroup">
			<label for="psychologicalSectionSummaryDocumentItems[${psychologicalSectionSummaryDocumentItemIndex}].fileDate" class="fieldLabel">
				<fmt:message key="fileDateLabel"/>
			</label>
			<fmt:formatDate var="psychologicalSectionSummaryDocumentDate" value="${psychologicalSectionSummaryDocumentItem.fileDate}" pattern="MM/dd/yyyy"/>
			<input type="text" value="${psychologicalSectionSummaryDocumentDate}" id="psychologicalSectionSummaryDocumentItems[${psychologicalSectionSummaryDocumentItemIndex}].fileDate"
					name="psychologicalSectionSummaryDocumentItems[${psychologicalSectionSummaryDocumentItemIndex}].fileDate" class="date" />
			<form:errors path="psychologicalSectionSummaryDocumentItems[${psychologicalSectionSummaryDocumentItemIndex}].fileDate" cssClass="error"/>
		</span>
		
		<span class="fieldGroup">
			<a id="createDocumentTagItemLink${psychologicalSectionSummaryDocumentItemIndex}" class="createLink addLink">
				<span class="visibleLinkLabel">
					<fmt:message key="addTagLink"/>
				</span>
			</a>
		</span>
		
		<span id="documentTagItems${psychologicalSectionSummaryDocumentItemIndex}">
			<c:forEach var="documentTagItem" items="${psychologicalSectionSummaryDocumentItems[psychologicalSectionSummaryDocumentItemIndex].documentTagItems}" varStatus="status">
				<c:set var="documentTagItem" value="${documentTagItem}" scope="request"/>
				<c:set var="documentTagItemIndex" value="${status.index}" scope="request"/>
				<c:if test="${not empty documentTagItem.itemOperation}">
					<jsp:include page="psychologicalSectionSummaryDocumentTagItemContent.jsp"/>
				</c:if>
			</c:forEach>
		</span>
		
		
		<span class="fieldGroup">
			<label for="psychologicalSectionSummaryDocumentItems[${psychologicalSectionSummaryDocumentItemIndex}].data" class="fieldLabel">
				<fmt:message key="documentLabel"/>
			</label>
			
			<c:choose>
				<c:when test="${not empty psychologicalSectionSummaryDocumentItem.document}">
					<c:set var="filename" value = "${psychologicalSectionSummaryDocumentItems[psychologicalSectionSummaryDocumentItemIndex].document.filename}"/>
					<fmt:formatDate value="${psychologicalSectionSummaryDocumentItems[psychologicalSectionSummaryDocumentItemIndex].document.date}"
							pattern="MM/dd/yyyy" var="documentDate"/>
					<a id="psychologicalSectionSummaryDocumentDownloadLink${psychologicalSectionSummaryDocumentItemIndex}"
		 					href="${pageContext.request.contextPath}/presentenceInvestigation/psychologicalSummary/retrieveFile.html?document=${psychologicalSectionSummaryDocumentItems[psychologicalSectionSummaryDocumentItemIndex].document.id}"
							class="downloadLink">
						<fmt:message key="titleExtensionFormat" bundle="${documentBundle}">
							<fmt:param value="${filename}"/><fmt:param value="${documentDate}"/>
						</fmt:message>
					</a>
					<input type="hidden" name="psychologicalSectionSummaryDocumentItems[${psychologicalSectionSummaryDocumentItemIndex}].data"
						id="psychologicalSectionSummaryDocumentItems[${psychologicalSectionSummaryDocumentItemIndex}].data"
						value="${psychologicalSectionSummaryDocumentItems[psychologicalSectionSummaryDocumentItemIndex].data}"/>
					<input type="hidden" name="psychologicalSectionSummaryDocumentItems[${psychologicalSectionSummaryDocumentItemIndex}].document"
						id="psychologicalSectionSummaryDocumentItems[${psychologicalSectionSummaryDocumentItemIndex}].document"
						value="${psychologicalSectionSummaryDocumentItems[psychologicalSectionSummaryDocumentItemIndex].document.id}"/>
				</c:when>
				<c:otherwise>
						<input id="psychologicalSectionSummaryDocumentItems[${psychologicalSectionSummaryDocumentItemIndex}].data" type="file"
								name="psychologicalSectionSummaryDocumentItems[${psychologicalSectionSummaryDocumentItemIndex}].data" />
				</c:otherwise>
			</c:choose>
			
			<input type="hidden" name="psychologicalSectionSummaryDocumentItems[${psychologicalSectionSummaryDocumentItemIndex}].fileExtension"
					id="psychologicalSectionSummaryDocumentItems[${psychologicalSectionSummaryDocumentItemIndex}].fileExtension"
					value="${psychologicalSectionSummaryDocumentItems[psychologicalSectionSummaryDocumentItemIndex].fileExtension}"/>
			<form:errors path="psychologicalSectionSummaryDocumentItems[${psychologicalSectionSummaryDocumentItemIndex}].data" cssClass="error"/>
		</span>
		
		
		
		
		
	</span>
</fmt:bundle>