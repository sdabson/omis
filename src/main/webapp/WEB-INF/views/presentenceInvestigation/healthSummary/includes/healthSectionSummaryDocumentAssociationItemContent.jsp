<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.document.msgs.document" var="documentBundle"/>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.healthSectionSummary">
	<span id="healthSectionSummaryDocumentAssociationItem${healthSectionSummaryDocumentAssociationItemIndex}" class="summaryDocumentItem">
		<a class="removeLink" id="removeHealthSectionSummaryDocumentAssociationLink${healthSectionSummaryDocumentAssociationItemIndex}"
		href="${pageContext.request.contextPath}/presentenceInvestigation/healthSummary/removeHealthSectionSummaryDocumentAssociation.html?healthSectionSummaryDocumentAssociation=${healthSectionSummaryDocumentAssociationItem.healthSectionSummaryDocumentAssociation.id}">
			<fmt:message key="removeLink" bundle="${commonBundle}"/>
		</a>
		<input type="hidden" id="healthSectionSummaryDocumentAssociationItemId${healthSectionSummaryDocumentAssociationItemIndex}"
				name="healthSectionSummaryDocumentAssociationItems[${healthSectionSummaryDocumentAssociationItemIndex}].healthSectionSummaryDocumentAssociation"
				value="${healthSectionSummaryDocumentAssociationItems[healthSectionSummaryDocumentAssociationItemIndex].healthSectionSummaryDocumentAssociation.id}"/>
		<form:errors path="healthSectionSummaryDocumentAssociationItems[${healthSectionSummaryDocumentAssociationItemIndex}].healthSectionSummaryDocumentAssociation" cssClass="error"/>
		<input type="hidden" id="healthSectionSummaryDocumentAssociationItemOperation${healthSectionSummaryDocumentAssociationItemIndex}"
				name="healthSectionSummaryDocumentAssociationItems[${healthSectionSummaryDocumentAssociationItemIndex}].itemOperation" value="${healthSectionSummaryDocumentAssociationItem.itemOperation}"/>
		<form:errors path="healthSectionSummaryDocumentAssociationItems[${healthSectionSummaryDocumentAssociationItemIndex}].itemOperation" cssClass="error"/>
		<span class="fieldGroup">
			<label for="healthSectionSummaryDocumentAssociationItems[${healthSectionSummaryDocumentAssociationItemIndex}].title" class="fieldLabel">
				<fmt:message key="documentTitleLabel"/>
			</label>
			<input type="text" id="healthSectionSummaryDocumentAssociationItems[${healthSectionSummaryDocumentAssociationItemIndex}].title"
					name="healthSectionSummaryDocumentAssociationItems[${healthSectionSummaryDocumentAssociationItemIndex}].title"
				value="${healthSectionSummaryDocumentAssociationItems[healthSectionSummaryDocumentAssociationItemIndex].title}" />
			<form:errors path="healthSectionSummaryDocumentAssociationItems[${healthSectionSummaryDocumentAssociationItemIndex}].title" cssClass="error"/>
		</span>
		
		<span class="fieldGroup">
			<label for="healthSectionSummaryDocumentAssociationItems[${healthSectionSummaryDocumentAssociationItemIndex}].fileDate" class="fieldLabel">
				<fmt:message key="fileDateLabel"/>
			</label>
			<fmt:formatDate var="healthSectionSummaryDocumentAssociationDate" value="${healthSectionSummaryDocumentAssociationItem.fileDate}" pattern="MM/dd/yyyy"/>
			<input type="text" value="${healthSectionSummaryDocumentAssociationDate}" id="healthSectionSummaryDocumentAssociationItems[${healthSectionSummaryDocumentAssociationItemIndex}].fileDate"
					name="healthSectionSummaryDocumentAssociationItems[${healthSectionSummaryDocumentAssociationItemIndex}].fileDate" class="date" />
			<form:errors path="healthSectionSummaryDocumentAssociationItems[${healthSectionSummaryDocumentAssociationItemIndex}].fileDate" cssClass="error"/>
		</span>
		
		<span class="fieldGroup">
			<a id="createDocumentTagItemLink${healthSectionSummaryDocumentAssociationItemIndex}" class="createLink addLink">
				<span class="visibleLinkLabel">
					<fmt:message key="addTagLink"/>
				</span>
			</a>
		</span>
		
		<span id="documentTagItems${healthSectionSummaryDocumentAssociationItemIndex}">		
			<c:forEach var="documentTagItem" items="${healthSectionSummaryDocumentAssociationItems[healthSectionSummaryDocumentAssociationItemIndex].documentTagItems}" varStatus="status">
				<c:set var="documentTagItem" value="${documentTagItem}" scope="request"/>
				<c:set var="documentTagItemIndex" value="${status.index}" scope="request"/>
				<c:if test="${not empty documentTagItem.itemOperation}">
					<jsp:include page="healthSectionSummaryDocumentTagItemContent.jsp"/>
				</c:if>
			</c:forEach>
		</span>		
		<span class="fieldGroup">
			<label for="healthSectionSummaryDocumentAssociationItems[${healthSectionSummaryDocumentAssociationItemIndex}].data" class="fieldLabel">
				<fmt:message key="documentLabel"/>
			</label>			
			<c:choose>
				<c:when test="${not empty healthSectionSummaryDocumentAssociationItem.document}">
					<c:set var="filename" value = "${healthSectionSummaryDocumentAssociationItems[healthSectionSummaryDocumentAssociationItemIndex].document.filename}"/>
					<fmt:formatDate value="${healthSectionSummaryDocumentAssociationItems[healthSectionSummaryDocumentAssociationItemIndex].document.date}"
							pattern="MM/dd/yyyy" var="documentDate"/>
					<a id="healthSectionSummaryDocumentDownloadLink${healthSectionSummaryDocumentAssociationItemIndex}"
		 					href="${pageContext.request.contextPath}/presentenceInvestigation/healthSummary/retrieveFile.html?document=${healthSectionSummaryDocumentAssociationItems[healthSectionSummaryDocumentAssociationItemIndex].document.id}"
							class="downloadLink">
						<fmt:message key="titleExtensionFormat" bundle="${documentBundle}">
							<fmt:param value="${filename}"/><fmt:param value="${documentDate}"/>
						</fmt:message>
					</a>
					<input type="hidden" name="healthSectionSummaryDocumentAssociationItems[${healthSectionSummaryDocumentAssociationItemIndex}].data"
						id="healthSectionSummaryDocumentAssociationItems[${healthSectionSummaryDocumentAssociationItemIndex}].data"
						value="${healthSectionSummaryDocumentAssociationItems[healthSectionSummaryDocumentAssociationItemIndex].data}"/>
					<input type="hidden" name="healthSectionSummaryDocumentAssociationItems[${healthSectionSummaryDocumentAssociationItemIndex}].document"
						id="healthSectionSummaryDocumentAssociationItems[${healthSectionSummaryDocumentAssociationItemIndex}].document"
						value="${healthSectionSummaryDocumentAssociationItems[healthSectionSummaryDocumentAssociationItemIndex].document.id}"/>
				</c:when>
				<c:otherwise>
						<input id="healthSectionSummaryDocumentAssociationItems[${healthSectionSummaryDocumentAssociationItemIndex}].data" type="file"
								name="healthSectionSummaryDocumentAssociationItems[${healthSectionSummaryDocumentAssociationItemIndex}].data" />
				</c:otherwise>
			</c:choose>
			
			<input type="hidden" name="healthSectionSummaryDocumentAssociationItems[${healthSectionSummaryDocumentAssociationItemIndex}].fileExtension"
					id="healthSectionSummaryDocumentAssociationItems[${healthSectionSummaryDocumentAssociationItemIndex}].fileExtension"
					value="${healthSectionSummaryDocumentAssociationItems[healthSectionSummaryDocumentAssociationItemIndex].fileExtension}"/>
			<form:errors path="healthSectionSummaryDocumentAssociationItems[${healthSectionSummaryDocumentAssociationItemIndex}].data" cssClass="error"/>
		</span>
	</span>
</fmt:bundle>