<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:setBundle basename="omis.document.msgs.document" var="documentBundle"/>
<fmt:bundle basename="omis.victim.msgs.victim">
	<span id="victimDocumentItem${victimDocumentItemIndex}" class="victimDocumentItem">
		<span class="removeLinkContainer">
			<a class="removeLink" id="removeVictimDocumentItemLink${victimDocumentItemIndex}" href="${pageContext.request.contextPath}/victim/removeVictimDocumentItem.html?victim=${victim.id}"><fmt:message key="removeLink" bundle="${commonBundle}"/></a>
		</span>
		<span class="fieldGroup">
			<label for="documentItems[${victimDocumentItemIndex}].docket" class="fieldLabel">
				<fmt:message key="docketLabel"/>
			</label>
			
			<select name="documentItems[${victimDocumentItemIndex}].docket" id="victimDocumentItem${victimDocumentItemIndex}Docket">
				<jsp:include page="../../../includes/nullOption.jsp"/>
				<c:forEach items="${dockets}" var="docket" varStatus="docketStatus">
					<c:choose>
						<c:when test="${victimDocumentItem.docket eq docket}">
							<option label="${docket.value}" value="${docket.id}" selected="selected">
								<fmt:message key="docketOptionLabel">
									<fmt:param value="${docket.value}"/>
									<fmt:param value="${docket.person.name.lastName}"/>
									<fmt:param value="${docket.person.name.firstName}"/>
								</fmt:message>
							</option>
						</c:when>
						<c:otherwise>
							<option label="${docket.value}" value="${docket.id}">
								<fmt:message key="docketOptionLabel">
									<fmt:param value="${docket.value}"/>
									<fmt:param value="${docket.person.name.lastName}"/>
									<fmt:param value="${docket.person.name.firstName}"/>
								</fmt:message>
							</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
		</span>
		<input type="hidden" id="victimDocumentId${victimDocumentItemIndex}"
				name="documentItems[${victimDocumentItemIndex}].association"
				value="${victimDocumentItem.association.id}"/>
		<form:errors path="documentItems[${victimDocumentItemIndex}].association" cssClass="error"/>
		<input type="hidden" id="victimDocumentOperation${victimDocumentItemIndex}"
				name="documentItems[${victimDocumentItemIndex}].operation" value="${victimDocumentItem.operation}"/>
		<form:errors path="documentItems[${victimDocumentItemIndex}].operation" cssClass="error"/>
		<span class="fieldGroup">
			<label for="documentItems[${victimDocumentItemIndex}].title" class="fieldLabel">
				<fmt:message key="documentTitleLabel"/>
			</label>
			<input type="text" id="documentItems[${victimDocumentItemIndex}].title"
					name="documentItems[${victimDocumentItemIndex}].title"
				value="${victimDocumentItem.title}" />
			<form:errors path="documentItems[${victimDocumentItemIndex}].title" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<label for="documentItems[${victimDocumentItemIndex}].date" class="fieldLabel">
				<fmt:message key="fileDateLabel"/>
			</label>
			<fmt:formatDate var="victimDocumentDate" value="${victimDocumentItem.date}" pattern="MM/dd/yyyy"/>
			<input type="text" value="${victimDocumentDate}" id="documentItems[${victimDocumentItemIndex}].date"
					name="documentItems[${victimDocumentItemIndex}].date" class="date" />
			<form:errors path="documentItems[${victimDocumentItemIndex}].date" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<a id="createDocumentTagItemLink${victimDocumentItemIndex}" class="createLink">
				<span class="visibleLinkLabel">
					<fmt:message key="addTagLink"/>
				</span>
			</a>
		</span>
		<span id="documentTagItems${victimDocumentItemIndex}" class="documentTagItemsFieldGroup">
			<c:forEach var="documentTagItem" items="${victimDocumentItem.tagItems}" varStatus="status">
				<c:set var="documentTagItem" value="${documentTagItem}" scope="request"/>
				<c:set var="documentTagItemIndex" value="${status.index}" scope="request"/>
				<c:if test="${not empty documentTagItem.documentTagOperation}">
					<jsp:include page="documentTagItem.jsp"/>
				</c:if>
			</c:forEach>
		</span>
		<span class="fieldGroup">
			<label for="documentItems[${victimDocumentItemIndex}].data" class="fieldLabel">
				<fmt:message key="documentLabel"/>
			</label>
			<c:choose>
				<c:when test="${not empty victimDocumentItem.association}">
					<c:set var="filename" value = "${victimDocumentItem.association.document.filename}"/>
					<fmt:formatDate value="${victimDocumentItem.association.document.date}"
							pattern="MM/dd/yyyy" var="documentDate"/>
					<a id="victimDocumentDownloadLink${victimDocumentItemIndex}"
		 					href="${pageContext.request.contextPath}/victim/retrieveFile.html?document=${victimDocumentItem.association.document.id}"
							class="downloadLink">
						<fmt:message key="titleExtensionFormat" bundle="${documentBundle}">
							<fmt:param value="${filename}"/><fmt:param value="${documentDate}"/>
						</fmt:message>
					</a>
					<input type="hidden" name="documentItems[${victimDocumentItemIndex}].documentData"
						id="documentItems[${victimDocumentItemIndex}].documentData"
						value="${victimDocumentItem.documentData}"/>
				</c:when>
				<c:otherwise>
						<input id="documentItems[${victimDocumentItemIndex}].documentData" type="file"
								name="documentItems[${victimDocumentItemIndex}].documentData" />
				</c:otherwise>
			</c:choose>
			<input type="hidden" name="documentItems[${victimDocumentItemIndex}].fileExtension"
					id="documentItems[${victimDocumentItemIndex}].fileExtension"
					value="${victimDocumentItem.fileExtension}"/>
			<form:errors path="documentItems[${victimDocumentItemIndex}].documentData" cssClass="error"/>
		</span>
		</span>
</fmt:bundle>