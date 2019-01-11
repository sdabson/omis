<%--
 - Author: Joel Norris
 - Version: 0.1.0 (November 28, 2018)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.media.msgs.media" var="mediaBundle"/>
<fmt:setBundle basename="omis.offenderphoto.msgs.offenderPhoto" var="offenderPhotoBundle"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<form:form commandName="offenderPhotoJoinForm" method="post" enctype="multipart/form-data" class="editForm">
<map name="enhancedImageEditMap">
	<area id="leftHalfPreview" class="hoverableMapSection" shape="rect" coords="0,0,240,270" alt="Left" href="javascript:void(0);">
    <area id="rightHalfPreview" class="hoverableMapSection" shape="rect" coords="241,0,480,270" alt="Right" href="javascript:void(0);">
</map>
	<fieldset>
		<legend><fmt:message key="offenderPhotoLabel" bundle="${offenderPhotoBundle}"/></legend>
		<span class="fieldGroup" id="photoPreviewFieldGroup">
			<label class="fieldLabel"><fmt:message key="photoPreviewLabel" bundle="${mediaBundle}"/></label>
<!-- 				<div id="previewImageWrapper" class="previewImageWrapper"> -->
				<c:choose>
					<c:when test="${not empty photoData}">
<%-- 						<img id="photoPreview" class="joinedPhotoPreview enhancedImageUploadResultImage hoverableImage" src="data:image/jpeg;base64,${photoData}" usemap="#enhancedImageEditMap"/> --%>
						<img id="photoPreview" class="joinedPhotoPreview enhancedImageUploadResultImage hoverableImage" src="data:image/jpeg;base64,${photoData}"/>
					</c:when>
					<c:otherwise>
					
<!-- 						<img id="photoPreview" class="joinedPhotoPreview viewableImage hoverableUploadImage" src="" usemap="#enhancedImageEditMap"/> -->
						<img id="photoPreview" class="joinedPhotoPreview viewableImage hoverableUploadImage" src="" />
					</c:otherwise>
				</c:choose>
<!-- 				</div> -->
				<c:choose>
					<c:when test="${not empty photoData}">
						<input id="photoData" name="photoData" type="hidden" value="${photoData}"/>
					</c:when>
					<c:otherwise>
						<input id="photoData" name="photoData" type="hidden" value=""/>
					</c:otherwise>
				</c:choose>
				<form:errors path="photoData" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="photoDate" class="fieldLabel">
		  		<fmt:message key="photoDateLabel" bundle="${mediaBundle}"/></form:label>
			<form:input path="photoDate" class="date"/>
			<form:errors path="photoDate" cssClass="error"/>
		</span>
		<div class="offenderPhotosContainer">
			<c:forEach var="offenderPhotoItem" items="${offenderPhotoJoinForm.photoItems}" varStatus="status">
				<div class="photoItem">
					<div class="photoItemThumbnailContainer">
					<c:choose>
						<c:when test="${not empty offenderPhotoItem.id}">
							<fmt:formatDate var="offenderPhotoDate" value="${offenderPhotoItem.date}" pattern="MM/dd/yyyy"/>
							<label class="photoItemDateLabel"><c:out value="${offenderPhotoDate}"/></label>
							<input type="hidden" class="date" name="photoItems[${status.index}].date" id="offenderPhotoAssociationNoteItemDate${offenderPhotoAssociationNoteItemIndex}" value="${offenderPhotoDate}"/>
							<img id="photoItemThumbnail${status.index}" class="viewableImage photoItemThumbnail" src="${pageContext.request.contextPath}/offenderPhoto/displayPhoto.html?association=${offenderPhotoItem.id}&amp;contentType=image/jpeg"/>
						</c:when>
						<c:otherwise>
							<input type="text" class="date" name="photoItems[${status.index}].date" id="offenderPhotoAssociationNoteItemDate${offenderPhotoAssociationNoteItemIndex}" value="${offenderPhotoItem.date}"/>
						</c:otherwise>
					</c:choose>
					</div>
					<div>
						<c:choose>
							<c:when test="${status.index eq offenderPhotoJoinForm.leftImage}">
								<input type="radio" name="leftImage" value="${status.index}" id="leftImage${status.index}" checked="checked"><label><fmt:message key="leftImageRadioLabel" bundle="${offenderPhotoBundle}"/></label>
							</c:when>
							<c:otherwise>
								<input type="radio" name="leftImage" value="${status.index}" id="leftImage${status.index}"><label><fmt:message key="leftImageRadioLabel" bundle="${offenderPhotoBundle}"/></label>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${status.index eq offenderPhotoJoinForm.rightImage}">
								<input type="radio" name="rightImage" value="${status.index}" id="rightImage${status.index}" checked="checked"><label><fmt:message key="rightImageRadioLabel" bundle="${offenderPhotoBundle}"/></label>
							</c:when>
							<c:otherwise>
								<input type="radio" name="rightImage" value="${status.index}" id="rightImage${status.index}"><label><fmt:message key="rightImageRadioLabel" bundle="${offenderPhotoBundle}"/></label>
							</c:otherwise>
						</c:choose>
						
					</div>
					<input type="hidden" name="photoItems[${status.index}].photoData" value="${offenderPhotoItem.photoData}"/>
					<input type="hidden" name="photoItems[${status.index}].id" value="${offenderPhotoItem.id}"/>
				</div>
			</c:forEach>
		</div>
	</fieldset>
	<fieldset>
		<legend><fmt:message key="offenderPhotoAssociationNotesLabel" bundle="${offenderPhotoBundle}"/></legend>
		<c:set var="offenderPhotoAssociationNoteItems" value="${offenderPhotoJoinForm.noteItems}" scope="request"/>
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