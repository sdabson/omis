
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<fmt:setBundle basename="omis.msgs.common" var="common" />
<fmt:setBundle basename="omis.document.msgs.document"
	var="documentBundle" />
<fmt:bundle basename="omis.probationparole.msgs.probationParoleDocument">
	<form:form commandName="probationParoleDocumentForm" class="editForm"
		method="post" enctype="multipart/form-data">
		<fieldset>
			<span class="fieldGroup">
				<form:label path="category" class="fieldLabel">
					<fmt:message key="categoryLabel"/>
				</form:label>
				<select name="category">
					<option value=""><fmt:message key="nullLabel" bundle="${common}"/></option>
					<c:forEach items="${categories}" var="category">
						<option value="${category.id}" ${category == probationParoleDocumentForm.category ? 'selected="selected"' : ''}>
							<c:out value="${category.name}"/>
						</option>
					</c:forEach>
				</select>
				<form:errors path="category" cssClass="error"/>
			</span>
			<span class="fieldGroup"> <form:label path="title"
					class="fieldLabel">
					<fmt:message key="titleLabel" />
				</form:label> <form:input path="title" /> <form:errors path="title"
					cssClass="error" />
			</span> <span class="fieldGroup"> <form:label path="date"
					class="fieldLabel">
					<fmt:message key="dateLabel" />
				</form:label> <form:input path="date" class="date" /> <form:errors path="date"
					cssClass="error" />
			</span>
			<c:set var="form" value="${probationParoleDocumentForm}" scope="request" />
			<jsp:include page="/WEB-INF/views/document/includes/documentTags.jsp" />
			<c:choose>
				<c:when test="${empty probationParoleDocumentAssociation}">
					<span class="fieldGroup"> <form:label path="data"
							class="fieldLabel">
							<fmt:message key="documentLabel" />
						</form:label> <input id="documentData" type="file" name="data"> <form:hidden
							path="fileExtension" id="dataFileExtension" /> <form:errors
							path="data" cssClass="error" />
					</span>
				</c:when>
				<c:otherwise>
					<form:input type="hidden" path="data" />
					<span class="fieldGroup"> <form:label path="data"
							class="fieldLabel">
							<fmt:message key="documentLabel" />
						</form:label> <c:set var="filename"
							value="${probationParoleDocumentAssociation.document.filename}" /> <a
						id="probationParoleDocumentDownloadLink"
						href="${pageContext.request.contextPath}/probationParole/document/retrieveFile.html?document=${probationParoleDocumentAssociation.document.id}"
						class="downloadLink"> <fmt:message key="titleExtensionFormat"
								bundle="${documentBundle}">
								<fmt:formatDate
									value="${probationParoleDocumentAssociation.document.date}"
									pattern="MM/dd/yyyy" var="documentDate" />
								<fmt:param value="${filename}" />
								<fmt:param value="${documentDate}" />
							</fmt:message>
					</a>
					</span>
				</c:otherwise>
			</c:choose>
		</fieldset>

		<c:if test="${not empty probationParoleDocumentAssociation}">
			<c:set var="updatable" value="${probationParoleDocumentAssociation}"
				scope="request" />
			<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp" />
		</c:if>
		<p class="buttons">
			<input type="submit"
				value="<fmt:message key='saveLabel' bundle="${common}"/>" />
		</p>
	</form:form>
</fmt:bundle>