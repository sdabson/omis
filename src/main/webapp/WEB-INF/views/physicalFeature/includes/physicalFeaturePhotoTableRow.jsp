<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="omis.media.msgs.media" var="mediaBundle"/>
<fmt:bundle basename="omis.physicalfeature.msgs.physicalfeature">
	<tr id="physicalFeaturePhotoRow${physicalFeaturePhotoIndex}" class="physicalFeaturePhotoRow">
		<td>
			<input type="hidden" id="physicalFeaturePhotoAssociationOperation${physicalFeaturePhotoIndex}" name="photoItems[${physicalFeaturePhotoIndex}].operation" value="${photoItem.operation}"/>
			<input type="hidden" name="photoItems[${physicalFeaturePhotoIndex}].photoAssociation" value="${photoItem.photoAssociation.id}"/>
			<a href="#delete.html" id="removePhysicalFeaturePhoto${physicalFeaturePhotoIndex}" class="removeLink"><span class="linkLabel"><fmt:message key="deletePhysicalFeaturePhotoLink"/></span></a>
		</td>
		<td>
			<c:if test="${empty photoItem.photoAssociation}">
				<input type="file" accept="image/jpeg" id="photoItems${physicalFeaturePhotoIndex}PhotoData" name="photoItems[${physicalFeaturePhotoIndex}].photoData"/>
				<form:errors cssClass="error" path="photoItems[${physicalFeaturePhotoIndex}]"/>
				<form:errors cssClass="error" path="photoItems[${physicalFeaturePhotoIndex}].photoData"/>
			</c:if>
		</td>
		<td>
			<span class="fieldGroup" id="photoPreviewFieldGroup${physicalFeaturePhotoIndex}">
				<label class="fieldLabel"><fmt:message key="photoPreviewLabel" bundle="${mediaBundle}"/></label>
				<c:choose>
					<c:when test="${not empty photoItem.photoAssociation}">
						<img id="photoPreview${physicalFeaturePhotoIndex}" class="viewableImage" height="120" width="180" src="${pageContext.request.contextPath}/physicalFeature/displayPhoto.html?photo=${physicalFeatureAssociationForm.photoItems[physicalFeaturePhotoIndex].photoAssociation.photo.id}&amp;contentType=image/jpg"/>
					</c:when>
					<c:otherwise>
						<img id="photoPreview${physicalFeaturePhotoIndex}" height="120" width="180" src=""/>
					</c:otherwise>
				</c:choose>
			</span>
		</td>
		<td>		
			<input id="photoItems[${physicalFeaturePhotoIndex}].date" name="photoItems[${physicalFeaturePhotoIndex}].date" class="dateSelect" value="<fmt:formatDate pattern='MM/dd/yyyy' value='${physicalFeatureAssociationForm.photoItems[physicalFeaturePhotoIndex].date}'/>"/>
			<form:errors cssClass="error" path="photoItems[${physicalFeaturePhotoIndex}].date"/>
		</td>
	</tr>
</fmt:bundle>