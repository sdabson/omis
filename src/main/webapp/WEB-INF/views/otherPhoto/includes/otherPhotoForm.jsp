<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:bundle basename="omis.physicalfeature.msgs.otherphoto">
	<form:form commandName="otherPhotosForm" method="post" enctype="multipart/form-data" class="editForm">
		<fieldset>
			<p class="instructions">
				<fmt:message key="otherPhotoWizardInstructionLabel"/>
			</p>
		</fieldset>
		<fieldset>
		<form:input type="hidden" path="offender"/>
			<legend><fmt:message key="physicalFeatureAndOrphanedPhotoAssociationLabel"/></legend>
			<div class="col-group">
				<div id="column1">
					<h2><fmt:message key="physicalFeaturesLabel"/></h2>
					<form:errors cssClass="error" path="physicalFeatureAssociation"/>
					<ul class="associationItems">
						<c:forEach var="pfAssociation" items="${pFAssociations}" varStatus="status">
							<li class="physicalFeature">
									<h3><c:out value="${pFAssociations[status.index].feature.name}"/></h3>
									<span class="operations">
									<c:choose>
										<c:when test="${pFAssociations[status.index].id eq otherPhotosForm.physicalFeatureAssociation.id}">
											<form:radiobutton id="physicalFeatureAssociation${pfAssociation.id}" path="physicalFeatureAssociation" class="physicalFeatureAssociation" value="${pFAssociations[status.index].id}" checked="checked"/>
										</c:when>
										<c:otherwise>
											<form:radiobutton id="physicalFeatureAssociation${pfAssociation.id}" path="physicalFeatureAssociation" class="physicalFeatureAssociation" value="${pFAssociations[status.index].id}"/>
										</c:otherwise>
									</c:choose>
									<label><fmt:message key="otherPhotoAssociateLabel"/></label>
									</span>
									<span class="associationListContent">
										<span class="fieldGroup">
											<label class="fieldLabel"><fmt:message key="offenderPhysicalFeatureItemOriginationDateLabel"/></label>
											<c:if test="${not empty pFAssociations[status.index].originationDate}">
												<label class="contentLabel"><fmt:formatDate value="${pFAssociations[status.index].originationDate}" pattern="MM/dd/yyyy"/></label>
											</c:if>
										</span>
										<span class="fieldGroup">
											<label class="fieldLabel"><fmt:message key="offenderPhysicalFeatureItemDescriptionLabel"/></label>
											<label class="contentLabel"><c:out value="${pFAssociations[status.index].description}"/></label>
										</span>
									</span>
							</li>
						</c:forEach>
					</ul>
				</div>
				<div id="column2">
					<h2><fmt:message key="orphanedPhotosLabel"/></h2>
					<form:errors cssClass="error" path="oPFPAItems"/>
					<ul class="associationItems">
						<c:forEach var="oPFPAItem" items="${otherPhotosForm.oPFPAItems}" varStatus="count">
							<li class="orphanedPhoto">
								<span class="operations" id="operations${count.index}">
									<form:input type="hidden" id="processStatus${count.index}" class="processStatus" path="oPFPAItems[${count.index}].status" value=""/>
									<span class="fieldGroup">
										<c:choose>
											<c:when test="${otherPhotosForm.oPFPAItems[count.index].status == 'ASSOCIATE'}">
												<form:input type="image" class="operationButton" id="associate${count.index}" src="${pageContext.request.contextPath}/resources/otherPhoto/images/acceptPressed.png" path="oPFPAItems[${count.index}].status" value="ASSOCIATE" alt="off"/>
											</c:when>
											<c:otherwise>
												<form:input type="image" class="operationButton" id="associate${count.index}" src="${pageContext.request.contextPath}/resources/otherPhoto/images/accept.png" path="oPFPAItems[${count.index}].status" value="ASSOCIATE" alt="off"/>
											</c:otherwise>
										</c:choose>
										<label><fmt:message key="otherPhotoAssociateLabel"/></label>
									</span>
									<span class="fieldGroup">
										<c:choose>
											<c:when test="${otherPhotosForm.oPFPAItems[count.index].status == 'REMOVE'}">
												<form:input type="image" class="operationButton" id="remove${count.index}" src="${pageContext.request.contextPath}/resources/otherPhoto/images/deletePressed.png" path="oPFPAItems[${count.index}].status" value="REMOVE" alt="off"/>
											</c:when>
											<c:otherwise>
												<form:input type="image" class="operationButton" id="remove${count.index}" src="${pageContext.request.contextPath}/resources/common/images/delete.png" path="oPFPAItems[${count.index}].status" value="REMOVE" alt="off"/>
											</c:otherwise>
										</c:choose>
										<label><fmt:message key="removeLabel"/></label>
									</span>
								</span>
								<span class="associationListContent">
									<span class="fieldGroup">
										<form:input type="hidden" path="oPFPAItems[${count.index}].physicalFeaturePhotoAssociation" value="${oPFPAItems[count.index].physicalFeaturePhotoAssociation.id}"/>
										<label class="fieldLabel"><c:out value="${otherPhotosForm.oPFPAItems[count.index].physicalFeaturePhotoAssociation.photo.filename}"/></label>
										<c:choose>
											<c:when test="${not empty otherPhotosForm.oPFPAItems[count.index].physicalFeaturePhotoAssociation.photo}">
												<img id="displayPhoto${count.index}" class="viewableImage" src="${pageContext.request.contextPath}/otherPhoto/displayPhoto.html?photo=${otherPhotosForm.oPFPAItems[count.index].physicalFeaturePhotoAssociation.photo.id}&amp;contentType=image/jpg"
													alt="<fmt:message key='noPhotoFoundLabel'/>" height="60" width="90"/>
											</c:when>
											<c:otherwise>
												<img id="displayPhoto${status.index}" src="#"
													alt="<fmt:message key='uploadPhotoLabel'/>" height="60" width="90"/>
											</c:otherwise>
										</c:choose>
									</span>
									<span class="fieldGroup">
										<label class="fieldLabel"><fmt:message key="otherPhotoDateLabel"/></label>
										<label class="contentLabel"><fmt:formatDate value='${otherPhotosForm.oPFPAItems[count.index].physicalFeaturePhotoAssociation.photo.date}' pattern="MM/dd/yyyy"/></label>
									</span>
								</span>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</fieldset>
		<p class="buttons">
			<input type="submit" value="<fmt:message key='otherPhotoAssociateLabel'/>"/>
		</p>
	</form:form>
</fmt:bundle>
