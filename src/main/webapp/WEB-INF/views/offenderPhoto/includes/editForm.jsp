<%--
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.media.msgs.media" var="mediaBundle"/>
<fmt:setBundle basename="omis.offenderphoto.msgs.offenderPhoto" var="offenderPhotoBundle"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<form:form commandName="offenderPhotoForm" method="post" enctype="multipart/form-data" class="editForm">
	<fieldset>
		<legend><fmt:message key="offenderPhotoLabel" bundle="${offenderPhotoBundle}"/></legend>
		<span class="fieldGroup" id="photoPreviewFieldGroup">
			<label class="fieldLabel">
				<fmt:message key="photoPreviewLabel" bundle="${mediaBundle}"/></label>
			<c:choose>
				<c:when test="${not empty association}">
					<img id="photoPreview" class="viewableImage" height="120" width="180" src="${pageContext.request.contextPath}/offenderPhoto/displayPhoto.html?association=${association.id}&amp;contentType=image/jpeg"/>
				</c:when>
				<c:otherwise>
					<img id="photoPreview" class="viewableImage" height="120" width="180" src=""/>
				</c:otherwise>
			</c:choose>
		</span>
		<c:if test="${empty association}">
			<span class="fieldGroup">
				<label for="photoData" class="fieldLabel">
					<fmt:message key="photoFileLabel" bundle="${mediaBundle}"/></label>
				<input id="photoData" type="file" name="photoData" accept="image/jpeg"/>
				<form:errors path="photoData" cssClass="error"/>
			</span>
		</c:if>
		<span class="fieldGroup">
			<form:label path="photoDate" class="fieldLabel">
		  		<fmt:message key="photoDateLabel" bundle="${mediaBundle}"/></form:label>
			<form:input path="photoDate" class="date"/>
			<form:errors path="photoDate" cssClass="error"/>
		</span>
	</fieldset>
	<fieldset>
		<legend><fmt:message key="offenderPhotoAssociationNotesLabel" bundle="${offenderPhotoBundle}"/></legend>
		<c:set var="offenderPhotoAssociationNoteItems" value="${offenderPhotoForm.noteItems}" scope="request"/>
		<jsp:include page="offenderPhotoAssociationNoteItemsTable.jsp"/>
	</fieldset>
	<c:if test="${not empty association}">
		<c:set var="updatable" value="${association}" scope="request"/>
		<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
	</c:if>
	<p class="buttons">
		<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
	</p>
</form:form>