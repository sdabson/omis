<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.document.msgs.document" var="documentBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.offenseSectionSummary">
	<span id="offenseSectionSummaryAssociableDocumentItem${offenseSectionSummaryAssociableDocumentItemIndex}" class="summaryDocumentItem">
		<a class="removeLink" id="removeOffenseSectionSummaryAssociableDocumentLink${offenseSectionSummaryAssociableDocumentItemIndex}"
		href="${pageContext.request.contextPath}/presentenceInvestigation/offenseSummary/removeOffenseSectionSummaryAssociableDocument.html?offenseSectionSummary=${offenseSectionSummary.id}">
			<fmt:message key="removeLink" bundle="${commonBundle}"/>
		</a>
		<input type="hidden" id="offenseSectionSummaryAssociableDocumentId${offenseSectionSummaryAssociableDocumentItemIndex}"
				name="offenseSectionSummaryAssociableDocumentItems[${offenseSectionSummaryAssociableDocumentItemIndex}].offenseSectionSummaryAssociableDocument"
				value="${offenseSectionSummaryAssociableDocumentItem.offenseSectionSummaryAssociableDocument.id}"/>
		<form:errors path="offenseSectionSummaryAssociableDocumentItems[${offenseSectionSummaryAssociableDocumentItemIndex}].offenseSectionSummaryAssociableDocument" cssClass="error"/>
		<input type="hidden" id="offenseSectionSummaryAssociableDocumentOperation${offenseSectionSummaryAssociableDocumentItemIndex}"
				name="offenseSectionSummaryAssociableDocumentItems[${offenseSectionSummaryAssociableDocumentItemIndex}].itemOperation" value="${offenseSectionSummaryAssociableDocumentItem.itemOperation}"/>
		<form:errors path="offenseSectionSummaryAssociableDocumentItems[${offenseSectionSummaryAssociableDocumentItemIndex}].itemOperation" cssClass="error"/>
		
		
		
		
		<span class="fieldGroup">
			<label for="offenseSectionSummaryAssociableDocumentItems[${offenseSectionSummaryAssociableDocumentItemIndex}].title" class="fieldLabel">
				<fmt:message key="documentTitleLabel"/>
			</label>
			<input type="text" id="offenseSectionSummaryAssociableDocumentItems[${offenseSectionSummaryAssociableDocumentItemIndex}].title"
					name="offenseSectionSummaryAssociableDocumentItems[${offenseSectionSummaryAssociableDocumentItemIndex}].title"
				value="${offenseSectionSummaryAssociableDocumentItems[offenseSectionSummaryAssociableDocumentItemIndex].title}" />
			<form:errors path="offenseSectionSummaryAssociableDocumentItems[${offenseSectionSummaryAssociableDocumentItemIndex}].title" cssClass="error"/>
		</span>
		
		<span class="fieldGroup">
			<label for="offenseSectionSummaryAssociableDocumentItems[${offenseSectionSummaryAssociableDocumentItemIndex}].fileDate" class="fieldLabel">
				<fmt:message key="fileDateLabel"/>
			</label>
			<fmt:formatDate var="offenseSectionSummaryAssociableDocumentDate" value="${offenseSectionSummaryAssociableDocumentItem.fileDate}" pattern="MM/dd/yyyy"/>
			<input type="text" value="${offenseSectionSummaryAssociableDocumentDate}" id="offenseSectionSummaryAssociableDocumentItems[${offenseSectionSummaryAssociableDocumentItemIndex}].fileDate"
					name="offenseSectionSummaryAssociableDocumentItems[${offenseSectionSummaryAssociableDocumentItemIndex}].fileDate" class="date" />
			<form:errors path="offenseSectionSummaryAssociableDocumentItems[${offenseSectionSummaryAssociableDocumentItemIndex}].fileDate" cssClass="error"/>
		</span>
		
		<span class="fieldGroup">
			<a id="createDocumentTagItemLink${offenseSectionSummaryAssociableDocumentItemIndex}" class="createLink addLink">
				<span class="visibleLinkLabel">
					<fmt:message key="addTagLink"/>
				</span>
			</a>
		</span>
		
		<span id="documentTagItems${offenseSectionSummaryAssociableDocumentItemIndex}">
			<c:forEach var="documentTagItem" items="${offenseSectionSummaryAssociableDocumentItems[offenseSectionSummaryAssociableDocumentItemIndex].documentTagItems}" varStatus="status">
				<c:set var="documentTagItem" value="${documentTagItem}" scope="request"/>
				<c:set var="documentTagItemIndex" value="${status.index}" scope="request"/>
				<c:if test="${not empty documentTagItem.itemOperation}">
					<jsp:include page="offenseSectionSummaryDocumentTagItemContent.jsp"/>
				</c:if>
			</c:forEach>
		</span>
		
		
		<span class="fieldGroup">
			<label for="offenseSectionSummaryAssociableDocumentItems[${offenseSectionSummaryAssociableDocumentItemIndex}].data" class="fieldLabel">
				<fmt:message key="documentLabel"/>
			</label>
			
			<c:choose>
				<c:when test="${not empty offenseSectionSummaryAssociableDocumentItem.document}">
					<c:set var="filename" value = "${offenseSectionSummaryAssociableDocumentItems[offenseSectionSummaryAssociableDocumentItemIndex].document.filename}"/>
					<fmt:formatDate value="${offenseSectionSummaryAssociableDocumentItems[offenseSectionSummaryAssociableDocumentItemIndex].document.date}"
							pattern="MM/dd/yyyy" var="documentDate"/>
					<a id="offenseSectionSummaryAssociableDocumentDownloadLink${offenseSectionSummaryAssociableDocumentItemIndex}"
		 					href="${pageContext.request.contextPath}/presentenceInvestigation/offenseSummary/retrieveFile.html?document=${offenseSectionSummaryAssociableDocumentItems[offenseSectionSummaryAssociableDocumentItemIndex].document.id}"
							class="downloadLink">
						<fmt:message key="titleExtensionFormat" bundle="${documentBundle}">
							<fmt:param value="${filename}"/><fmt:param value="${documentDate}"/>
						</fmt:message>
					</a>
					<input type="hidden" name="offenseSectionSummaryAssociableDocumentItems[${offenseSectionSummaryAssociableDocumentItemIndex}].data"
						id="offenseSectionSummaryAssociableDocumentItems[${offenseSectionSummaryAssociableDocumentItemIndex}].data"
						value="${offenseSectionSummaryAssociableDocumentItems[offenseSectionSummaryAssociableDocumentItemIndex].data}"/>
					<input type="hidden" name="offenseSectionSummaryAssociableDocumentItems[${offenseSectionSummaryAssociableDocumentItemIndex}].document"
						id="offenseSectionSummaryAssociableDocumentItems[${offenseSectionSummaryAssociableDocumentItemIndex}].document"
						value="${offenseSectionSummaryAssociableDocumentItems[offenseSectionSummaryAssociableDocumentItemIndex].document.id}"/>
				</c:when>
				<c:otherwise>
						<input id="offenseSectionSummaryAssociableDocumentItems[${offenseSectionSummaryAssociableDocumentItemIndex}].data" type="file"
								name="offenseSectionSummaryAssociableDocumentItems[${offenseSectionSummaryAssociableDocumentItemIndex}].data" />
				</c:otherwise>
			</c:choose>
			
			<input type="hidden" name="offenseSectionSummaryAssociableDocumentItems[${offenseSectionSummaryAssociableDocumentItemIndex}].fileExtension"
					id="offenseSectionSummaryAssociableDocumentItems[${offenseSectionSummaryAssociableDocumentItemIndex}].fileExtension"
					value="${offenseSectionSummaryAssociableDocumentItems[offenseSectionSummaryAssociableDocumentItemIndex].fileExtension}"/>
			<form:errors path="offenseSectionSummaryAssociableDocumentItems[${offenseSectionSummaryAssociableDocumentItemIndex}].data" cssClass="error"/>
		</span>
		
		
		
		
		
	</span>
</fmt:bundle>