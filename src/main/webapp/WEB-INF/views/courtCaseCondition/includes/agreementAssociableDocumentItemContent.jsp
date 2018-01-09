<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.document.msgs.document" var="documentBundle"/>
<fmt:bundle basename="omis.courtcasecondition.msgs.courtCaseConditions">
	<c:choose>
		<c:when test="${agreementAssociableDocumentItem.itemOperation == 'REMOVE'}">
			<c:set value="removeContent" var="agreementAssociableDisplayClass"/>
		</c:when>
		<c:otherwise>
			<c:set var="agreementAssociableDisplayClass" value=""/>
		</c:otherwise>
	</c:choose>
	<span id="agreementAssociableDocumentItem${agreementAssociableDocumentItemIndex}" class="agreementAssociableDocumentItem ${agreementAssociableDisplayClass}">
		<a class="removeLink" id="removeAgreementAssociableDocumentLink${agreementAssociableDocumentItemIndex}"
		href="${pageContext.request.contextPath}/courtCaseCondition/removeAgreementAssociableDocument.html?agreementAssociable=${agreementAssociable.id}">
			<fmt:message key="removeLink" bundle="${commonBundle}"/>
		</a>
		<input type="hidden" id="agreementAssociableDocumentId${agreementAssociableDocumentItemIndex}"
				name="agreementAssociableDocumentItems[${agreementAssociableDocumentItemIndex}].agreementAssociableDocument"
				value="${agreementAssociableDocumentItem.agreementAssociableDocument.id}"/>
		<input type="hidden" name="agreementAssociableDocumentItems[${agreementAssociableDocumentItemIndex}].document"
						id="agreementAssociableDocumentItems[${agreementAssociableDocumentItemIndex}].document"
						value="${agreementAssociableDocumentItems[agreementAssociableDocumentItemIndex].document.id}"/>
		<form:errors path="agreementAssociableDocumentItems[${agreementAssociableDocumentItemIndex}].agreementAssociableDocument" cssClass="error"/>
		<input type="hidden" id="agreementAssociableDocumentOperation${agreementAssociableDocumentItemIndex}"
				name="agreementAssociableDocumentItems[${agreementAssociableDocumentItemIndex}].itemOperation" value="${agreementAssociableDocumentItem.itemOperation}"/>
		<form:errors path="agreementAssociableDocumentItems[${agreementAssociableDocumentItemIndex}].itemOperation" cssClass="error"/>
		<span class="fieldGroup">
			<label for="agreementAssociableDocumentItems[${agreementAssociableDocumentItemIndex}].title" class="fieldLabel">
				<fmt:message key="documentTitleLabel"/>
			</label>
			<input type="text" id="agreementAssociableDocumentItems[${agreementAssociableDocumentItemIndex}].title"
					name="agreementAssociableDocumentItems[${agreementAssociableDocumentItemIndex}].title"
				value="${agreementAssociableDocumentItems[agreementAssociableDocumentItemIndex].title}" />
			<form:errors path="agreementAssociableDocumentItems[${agreementAssociableDocumentItemIndex}].title" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<label for="agreementAssociableDocumentItems[${agreementAssociableDocumentItemIndex}].date" class="fieldLabel">
				<fmt:message key="fileDateLabel"/>
			</label>
			<fmt:formatDate var="agreementAssociableDocumentDate" value="${agreementAssociableDocumentItem.date}" pattern="MM/dd/yyyy"/>
			<input type="text" value="${agreementAssociableDocumentDate}" id="agreementAssociableDocumentItems[${agreementAssociableDocumentItemIndex}].date"
					name="agreementAssociableDocumentItems[${agreementAssociableDocumentItemIndex}].date" class="date" />
			<form:errors path="agreementAssociableDocumentItems[${agreementAssociableDocumentItemIndex}].date" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<a id="createDocumentTagItemLink${agreementAssociableDocumentItemIndex}" class="createLink addLink">
				<span class="visibleLinkLabel">
					<fmt:message key="addTagLink"/>
				</span>
			</a>
		</span>
		<span id="documentTagItems${agreementAssociableDocumentItemIndex}">
			<c:forEach var="documentTagItem" items="${agreementAssociableDocumentItems[agreementAssociableDocumentItemIndex].tagItems}" varStatus="status">
				<c:set var="documentTagItem" value="${documentTagItem}" scope="request"/>
				<c:set var="documentTagItemIndex" value="${status.index}" scope="request"/>
				<c:if test="${not empty documentTagItem.documentTagOperation}">
					<jsp:include page="documentTagItemContent.jsp"/>
				</c:if>
			</c:forEach>
		</span>
		<span class="fieldGroup">
			<label for="agreementAssociableDocumentItems[${agreementAssociableDocumentItemIndex}].data" class="fieldLabel">
				<fmt:message key="documentLabel"/>
			</label>
			<c:choose>
				<c:when test="${not empty agreementAssociableDocumentItem.document}">
					<c:set var="filename" value = "${agreementAssociableDocumentItems[agreementAssociableDocumentItemIndex].document.filename}"/>
					<fmt:formatDate value="${agreementAssociableDocumentItems[agreementAssociableDocumentItemIndex].document.date}"
							pattern="MM/dd/yyyy" var="documentDate"/>
					<a id="agreementAssociableDocumentDownloadLink${agreementAssociableDocumentItemIndex}"
		 					href="${pageContext.request.contextPath}/courtCaseCondition/retrieveFile.html?document=${agreementAssociableDocumentItems[agreementAssociableDocumentItemIndex].document.id}"
							class="downloadLink">
						<fmt:message key="titleExtensionFormat" bundle="${documentBundle}">
							<fmt:param value="${filename}"/><fmt:param value="${documentDate}"/>
						</fmt:message>
					</a>
					<input type="hidden" name="agreementAssociableDocumentItems[${agreementAssociableDocumentItemIndex}].data"
						id="agreementAssociableDocumentItems[${agreementAssociableDocumentItemIndex}].data"
						value="${agreementAssociableDocumentItems[agreementAssociableDocumentItemIndex].data}"/>
				</c:when>
				<c:otherwise>
						<input id="agreementAssociableDocumentItems[${agreementAssociableDocumentItemIndex}].data" type="file"
								name="agreementAssociableDocumentItems[${agreementAssociableDocumentItemIndex}].data" />
				</c:otherwise>
			</c:choose>
			<input type="hidden" name="agreementAssociableDocumentItems[${agreementAssociableDocumentItemIndex}].fileExtension"
					id="agreementAssociableDocumentItems[${agreementAssociableDocumentItemIndex}].fileExtension"
					value="${agreementAssociableDocumentItems[agreementAssociableDocumentItemIndex].fileExtension}"/>
			<form:errors path="agreementAssociableDocumentItems[${agreementAssociableDocumentItemIndex}].data" cssClass="error"/>
		</span>
	</span>
</fmt:bundle>