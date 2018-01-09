<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.document.msgs.document" var="documentBundle"/>
<fmt:bundle basename="omis.violationevent.msgs.violationEvent">
	<c:choose>
		<c:when test="${violationEventDocumentItem.itemOperation == 'REMOVE'}">
			<c:set value="removeContent" var="violationEventDisplayClass"/>
		</c:when>
		<c:otherwise>
			<c:set var="violationEventDisplayClass" value=""/>
		</c:otherwise>
	</c:choose>
	<span id="violationEventDocumentItem${violationEventDocumentItemIndex}" class="violationEventDocumentItem ${violationEventDisplayClass}">
		<a class="removeLink" id="removeViolationEventDocumentLink${violationEventDocumentItemIndex}"
		href="${pageContext.request.contextPath}/violationEvent/removeViolationEventDocument.html?violationEvent=${violationEvent.id}">
			<fmt:message key="removeLink" bundle="${commonBundle}"/>
		</a>
		<input type="hidden" id="violationEventDocumentId${violationEventDocumentItemIndex}"
				name="violationEventDocumentItems[${violationEventDocumentItemIndex}].violationEventDocument"
				value="${violationEventDocumentItem.violationEventDocument.id}"/>
		<form:errors path="violationEventDocumentItems[${violationEventDocumentItemIndex}].violationEventDocument" cssClass="error"/>
		<input type="hidden" id="violationEventDocumentOperation${violationEventDocumentItemIndex}"
				name="violationEventDocumentItems[${violationEventDocumentItemIndex}].itemOperation" value="${violationEventDocumentItem.itemOperation}"/>
		<form:errors path="violationEventDocumentItems[${violationEventDocumentItemIndex}].itemOperation" cssClass="error"/>
		
		
		
		
		<span class="fieldGroup">
			<label for="violationEventDocumentItems[${violationEventDocumentItemIndex}].title" class="fieldLabel">
				<fmt:message key="documentTitleLabel"/>
			</label>
			<input type="text" id="violationEventDocumentItems[${violationEventDocumentItemIndex}].title"
					name="violationEventDocumentItems[${violationEventDocumentItemIndex}].title"
				value="${violationEventDocumentItems[violationEventDocumentItemIndex].title}" />
			<form:errors path="violationEventDocumentItems[${violationEventDocumentItemIndex}].title" cssClass="error"/>
		</span>
		
		<span class="fieldGroup">
			<label for="violationEventDocumentItems[${violationEventDocumentItemIndex}].date" class="fieldLabel">
				<fmt:message key="fileDateLabel"/>
			</label>
			<fmt:formatDate var="violationEventDocumentDate" value="${violationEventDocumentItem.date}" pattern="MM/dd/yyyy"/>
			<input type="text" value="${violationEventDocumentDate}" id="violationEventDocumentItems[${violationEventDocumentItemIndex}].date"
					name="violationEventDocumentItems[${violationEventDocumentItemIndex}].date" class="date" />
			<form:errors path="violationEventDocumentItems[${violationEventDocumentItemIndex}].date" cssClass="error"/>
		</span>
		
		<span class="fieldGroup">
			<a id="createDocumentTagItemLink${violationEventDocumentItemIndex}" class="createLink addLink">
				<span class="visibleLinkLabel">
					<fmt:message key="addTagLink"/>
				</span>
			</a>
		</span>
		
		<span id="documentTagItems${violationEventDocumentItemIndex}">
			<c:forEach var="documentTagItem" items="${violationEventDocumentItems[violationEventDocumentItemIndex].tagItems}" varStatus="status">
				<c:set var="documentTagItem" value="${documentTagItem}" scope="request"/>
				<c:set var="documentTagItemIndex" value="${status.index}" scope="request"/>
				<c:if test="${not empty documentTagItem.documentTagOperation}">
					<jsp:include page="documentTagItemContent.jsp"/>
				</c:if>
			</c:forEach>
		</span>
		
		
		<span class="fieldGroup">
			<label for="violationEventDocumentItems[${violationEventDocumentItemIndex}].data" class="fieldLabel">
				<fmt:message key="documentLabel"/>
			</label>
			
			<c:choose>
				<c:when test="${not empty violationEventDocumentItem.document}">
					<c:set var="filename" value = "${violationEventDocumentItems[violationEventDocumentItemIndex].document.filename}"/>
					<fmt:formatDate value="${violationEventDocumentItems[violationEventDocumentItemIndex].document.date}"
							pattern="MM/dd/yyyy" var="documentDate"/>
					<a id="violationEventDocumentDownloadLink${violationEventDocumentItemIndex}"
		 					href="${pageContext.request.contextPath}/violationEvent/retrieveFile.html?document=${violationEventDocumentItems[violationEventDocumentItemIndex].document.id}"
							class="downloadLink">
						<fmt:message key="titleExtensionFormat" bundle="${documentBundle}">
							<fmt:param value="${filename}"/><fmt:param value="${documentDate}"/>
						</fmt:message>
					</a>
					<input type="hidden" name="violationEventDocumentItems[${violationEventDocumentItemIndex}].data"
						id="violationEventDocumentItems[${violationEventDocumentItemIndex}].data"
						value="${violationEventDocumentItems[violationEventDocumentItemIndex].data}"/>
					<input type="hidden" name="violationEventDocumentItems[${violationEventDocumentItemIndex}].document"
						id="violationEventDocumentItems[${violationEventDocumentItemIndex}].document"
						value="${violationEventDocumentItems[violationEventDocumentItemIndex].document.id}"/>
				</c:when>
				<c:otherwise>
						<input id="violationEventDocumentItems[${violationEventDocumentItemIndex}].data" type="file"
								name="violationEventDocumentItems[${violationEventDocumentItemIndex}].data" />
				</c:otherwise>
			</c:choose>
			
			<input type="hidden" name="violationEventDocumentItems[${violationEventDocumentItemIndex}].fileExtension"
					id="violationEventDocumentItems[${violationEventDocumentItemIndex}].fileExtension"
					value="${violationEventDocumentItems[violationEventDocumentItemIndex].fileExtension}"/>
			<form:errors path="violationEventDocumentItems[${violationEventDocumentItemIndex}].data" cssClass="error"/>
		</span>
		
		
		
		
		
	</span>
</fmt:bundle>