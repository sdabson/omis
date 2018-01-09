<%-- Author: Ryan Johns
 - Version: 0.1.0 (Dec 4, 2015)
 - Since: OMIS 3.0 --%>
 <%@ page trimDirectiveWhitespaces="true" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <fmt:setBundle basename="omis.msgs.common" var="common"/>
 <fmt:setBundle basename="omis.document.msgs.document" var="documentBundle"/>
 <fmt:bundle basename="omis.courtdocument.msgs.document">
 <form:form commandName="form" class="editForm" method="post" enctype="multipart/form-data">
 	<fieldset>
 		<legend><fmt:message key="courtCaseGroupLabel"/></legend>
 		<span class="fieldGroup">
 			<form:label path="courtCase" class="fieldLabel">
 				<fmt:message key="courtCaseLabel"/>
 			</form:label>
 			<select name="courtCase">
 				<c:forEach items="${courtCases}" var="courtCase">
 				<c:choose>
 				<c:when test="${courtCase eq form.courtCase }">
 				<option value="${courtCase.id}" selected="selected">
 					<fmt:message key="courtCaseFormat">
	 					<fmt:param value="${courtCase.docket.value}"/>
	 					<fmt:param value="${courtCase.docket.court.name}"/>
 					</fmt:message>
 				</option>
 				</c:when>
 				<c:otherwise>
 				<option value="${courtCase.id}">
 					<fmt:message key="courtCaseFormat">
	 					<fmt:param value="${courtCase.docket.value}"/>
	 					<fmt:param value="${courtCase.docket.court.name}"/>
 					</fmt:message>
 				</option>
 				</c:otherwise>
 				</c:choose>
 				</c:forEach>
 			</select>
 			<form:errors path="courtCase" cssClass="error"/>
 		</span>
 		<span class="fieldGroup">
 			<form:label path="courtDocumentCategory" class="fieldLabel">
 				<fmt:message key="categoryLabel"/>
 			</form:label>
 			<select name="courtDocumentCategory">
 				<c:forEach items="${categories}" var="category">
 				<c:choose>
 				<c:when test="${category eq form.courtDocumentCategory}">
 				<option value="${category.id}" selected="selected">
 					<c:out value="${category.name}"/>
 				</option>
 				</c:when>
 				<c:otherwise>
 				<option value="${category.id}">
 					<c:out value="${category.name}"/>
 				</option>
 				</c:otherwise>
 				</c:choose>
 				</c:forEach>
 			</select>
 			<form:errors path="courtDocumentCategory" cssClass="error"/>
 		</span>
 	</fieldset>
 	<fieldset>
 		<legend><fmt:message key="documentGroupLabel"/></legend>
 		<span class="fieldGroup">
 			<form:label path="title" class="fieldLabel">
 				<fmt:message key="titleLabel"/>
 			</form:label>
 			<form:input path="title"/>
 			<form:errors path="title" cssClass="error"/>
 		</span>
 		<span class="fieldGroup">
 			<form:label path="date" class="fieldLabel">
 				<fmt:message key="dateLabel"/>
 			</form:label>
 			<form:input path="date" class="date"/>
 			<form:errors path="date" cssClass="error"/>
 		</span>
 		<jsp:include page="/WEB-INF/views/document/includes/documentTags.jsp"/>
 		<c:choose>
 		<c:when test="${not editing}">
 		<span class="fieldGroup">
 			<form:label path="data" class="fieldLabel">
 				<fmt:message key="dataLabel"/>
 			</form:label>
 			<input id="documentData" type="file" type="file" name="data">
 			<form:hidden path="fileExtension" id="dataFileExtension"/>
 			<form:errors path="data" cssClass="error"/>
 		</span>
 		</c:when>
 		<c:otherwise>
 			<span class="fieldGroup">
 				<form:label path="data" class="fieldLabel">
 					<fmt:message key="dataLabel"/>
 				</form:label>
 				<c:set var="filename" value = "${courtCaseDocumentAssociation.document.filename}"/>
 				<a id="courtCaseDocumentDownloadLink" href="${pageContext.request.contextPath}/documents/retrieveFile.html?document=${courtCaseDocumentAssociation.document.id}" class="downloadLink"> 
 				<fmt:message key="titleExtensionFormat" bundle="${documentBundle}">
 					<fmt:formatDate value="${courtCaseDocumentAssociation.document.date}" pattern="MM/dd/yyyy" var="documentDate"/>
 					<fmt:param value="${filename}"/><fmt:param value="${documentDate}"/>
 				</fmt:message>
 				</a>
 			</span>
 		</c:otherwise>
 		</c:choose>
 	</fieldset>
 	
 	<c:if test="${not empty courtCaseDocumentAssociation}">
	<c:set var="updatable" value="${courtCaseDocumentAssociation}" scope="request"/>
		<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
	</c:if>
	<p class="buttons">
		<input type="submit" value="<fmt:message key='saveLabel' bundle="${common}"/>"/>
	</p>
</form:form>
 </fmt:bundle>