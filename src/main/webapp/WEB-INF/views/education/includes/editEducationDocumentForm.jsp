 <%@ page trimDirectiveWhitespaces="true" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <fmt:setBundle basename="omis.msgs.common" var="common"/>
 <fmt:setBundle basename="omis.document.msgs.document" var="documentBundle"/>
 <fmt:bundle basename="omis.education.msgs.educationDocument">
 <form:form commandName="educationDocumentForm" class="editForm" method="post" enctype="multipart/form-data">
 	<fieldset>
 		<span class="fieldGroup">
 			<form:label path="category" class="fieldLabel">
 				<fmt:message key="categoryLabel" />
 			</form:label>
 			<form:select path="category">
				<jsp:include page="../../includes/nullOption.jsp"/>
				<c:forEach items="${categories}" var="cat">
					<option value="${cat.id}" ${cat == educationDocumentForm.category ? 'selected="selected"' : ''} >
						<c:out value="${cat.name}"/>
					</option>
				</c:forEach>
			</form:select>
 			<form:errors path="category" cssClass="error"/>
 		</span>
 		<span class="fieldGroup">
 			<form:label path="title" class="fieldLabel">
 				<fmt:message key="titleLabel" />
 			</form:label>
 			<form:input path="title"/>
 			<form:errors path="title" cssClass="error"/>
 		</span>
 		<span class="fieldGroup">
 			<form:label path="date" class="fieldLabel">
 				<fmt:message key="dateLabel" />
 			</form:label>
 			<form:input path="date" class="date"/>
 			<form:errors path="date" cssClass="error"/>
 		</span>
 		<c:set var="form" value="${educationDocumentForm}" scope="request" />
 		<jsp:include page="/WEB-INF/views/document/includes/documentTags.jsp"/>
 		<c:choose>
 		<c:when test="${empty educationAssociableDocument}">
 		<span class="fieldGroup">
 			<form:label path="data" class="fieldLabel">
 				<fmt:message key="documentLabel" />
 			</form:label>
 			<input id="documentData" type="file" name="data">
 			<form:hidden path="fileExtension" id="dataFileExtension"/>
 			<form:errors path="data" cssClass="error"/>
 		</span>
 		</c:when>
 		<c:otherwise>
 			<form:input type="hidden" path="data" />
 			<span class="fieldGroup">
 				<form:label path="data" class="fieldLabel">
 					<fmt:message key="documentLabel" />
 				</form:label>
 				<c:set var="filename" value = "${educationAssociableDocument.document.filename}"/>
 				<a id="educationDocumentDownloadLink" href="${pageContext.request.contextPath}/education/document/retrieveFile.html?document=${educationAssociableDocument.document.id}" class="downloadLink"> 
 				<fmt:message key="titleExtensionFormat" bundle="${documentBundle}">
 					<fmt:formatDate value="${educationAssociableDocument.document.date}" pattern="MM/dd/yyyy" var="documentDate"/>
 					<fmt:param value="${filename}"/><fmt:param value="${documentDate}"/>
 				</fmt:message>
 				</a>
 			</span>
 		</c:otherwise>
 		</c:choose>
 	</fieldset>
 	
 	<c:if test="${not empty educationAssociableDocument}">
	<c:set var="updatable" value="${educationAssociableDocument}" scope="request"/>
		<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
	</c:if>
	<p class="buttons">
		<input type="submit" value="<fmt:message key='saveLabel' bundle="${common}"/>"/>
	</p>
</form:form>
 </fmt:bundle>