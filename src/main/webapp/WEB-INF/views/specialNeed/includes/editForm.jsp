<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.document.msgs.document" var="documentBundle"/>
<fmt:bundle basename="omis.specialneed.msgs.specialNeed">
<form:form commandName="specialNeedForm" class="editForm" method="post" enctype="multipart/form-data">
	<fieldset>
		<legend><fmt:message key="specialNeedDetailsLabel"/></legend>		
		<form:input type="hidden" path="classification" id="classification"/>
		<span class="fieldGroup">
			<form:label path="category" class="fieldLabel"><fmt:message key="specialNeedCategoryLabel"/></form:label>
			<form:select path="category" id="category">
					<jsp:include page="specialNeedCategoryOptions.jsp"/>
			</form:select>
			<form:errors cssClass="error" path="category"/>
		</span>
		<span class="fieldGroup">
			<form:label path="source" class="fieldLabel"><fmt:message key="specialNeedSourceLabel"/></form:label>
			<form:select path="source">
					<form:option value="">...</form:option>
					<form:options items="${sources}" itemLabel="name" itemValue="id"/>
			</form:select>
			<form:errors cssClass="error" path="source"/>
		</span>
		<span class="fieldGroup">
			<fmt:message key="sourceCommentDescription" var="sourceCommentDescription"/>
			<form:label path="sourceComment" class="fieldLabel"><fmt:message key="specialNeedSourceComment"/></form:label>
			<form:textarea path="sourceComment" maxlength="4000" rows="6" placeholder="${sourceCommentDescription}"/>
				<span id="sourceCommentCharacterCounter">
				</span>
			<form:errors cssClass="error" path="sourceComment"/>
		</span>
		<span class="fieldGroup">
			<form:label path="startDate" class="fieldLabel"><fmt:message key="specialNeedStartDateLabel"/></form:label>
			<form:input path="startDate" class="date"/>
			<form:errors cssClass="error" path="startDate"/>
		</span>
		<span class="fieldGroup">
			<form:label path="endDate" class="fieldLabel"><fmt:message key="specialNeedEndDateLabel"/></form:label>
			<form:input path="endDate" class="date"/>
			<form:errors cssClass="error" path="endDate"/>
		</span>	
		<span class="fieldGroup">
			<fmt:message key="commentDescription" var="commentDescription"/>
			<form:label path="comment" class="fieldLabel"><fmt:message key="specialNeedComment"/></form:label>
			<form:textarea path="comment" maxlength="4000" rows="6" placeholder="${commentDescription}"/>
				<span id="commentCharacterCounter">
				</span>
			<form:errors cssClass="error" path="comment"/>
		</span>
	</fieldset>
	<fieldset>
		<legend><fmt:message key="specialNeedDocumentLabel"/></legend>
		<span id="specialNeedDocument" class="specialNeedDocument">
			<span class="fieldGroup">
			<input type="hidden" name="documentItemOperation" id="documentItemOperation" value="${specialNeedForm.documentItemOperation.name}"/>
			<a id="removeDocumentLink" class="removeLink" href="${pageContext.request.contextPath}/specialNeed/removeDocument.html">Remove Document</a>
			</span>
	 		<span class="fieldGroup">
	 			<form:label path="documentCategory" class="fieldLabel">
	 				<fmt:message key="documentCategoryLabel" />
	 			</form:label>
	 			<form:select path="documentCategory">
					<jsp:include page="../../includes/nullOption.jsp"/>
					<c:forEach items="${documentCategories}" var="cat">
						<option value="${cat.id}" ${cat == specialNeedForm.documentCategory ? 'selected="selected"' : ''} >
							<c:out value="${cat.name}"/>
						</option>
					</c:forEach>
				</form:select>
	 			<form:errors path="documentCategory" cssClass="error"/>
	 		</span>
	 		<span class="fieldGroup">
	 			<form:label path="title" class="fieldLabel">
	 				<fmt:message key="documentTitleLabel" />
	 			</form:label>
	 			<form:input path="title"/>
	 			<form:errors path="title" cssClass="error"/>
	 		</span>
	 		<span class="fieldGroup">
	 			<form:label path="date" class="fieldLabel">
	 				<fmt:message key="documentDateLabel" />
	 			</form:label>
	 			<form:input path="date" class="date"/>
	 			<form:errors path="date" cssClass="error"/>
	 		</span>
	 		<c:set var="form" value="${specialNeedForm}" scope="request" />
	 		<jsp:include page="/WEB-INF/views/document/includes/documentTags.jsp"/>
	 		<c:choose>
	 		<c:when test="${empty specialNeedAssociableDocument or specialNeedForm.documentItemOperation.name eq 'CREATE'}">
	 			<c:set var="inputDisabled" value=""/>
	 			<c:set var="inputClass" value=""/>
	 			<c:set var="displayDisabled" value="disabled=\"disabled\""/>
	 			<c:set var="displayClass" value="hidden"/>
	 		</c:when>
	 		<c:otherwise>
		 		<c:set var="inputDisabled" value="disabled=\"disabled\""/>
		 		<c:set var="inputClass" value="hidden"/>
		 		<c:set var="displayDisabled" value=""/>
		 		<c:set var="displayClass" value=""/>
	 		</c:otherwise>
	 		</c:choose>
	 		<span class="fieldGroup">
	 			<form:label path="data" class="fieldLabel">
	 				<fmt:message key="documentLabel" />
	 			</form:label>
	 			<input id="documentData" type="file" name="data" ${inputDisabled} class="${inputClass}">
	 			<form:hidden path="fileExtension" id="dataFileExtension"/>
	 			<form:errors path="data" cssClass="error"/>
	 			
	 			<input id="data" name="data" type="hidden" ${displayDisabled}/>
	 			<c:set var="filename" value="${specialNeedAssociableDocument.document.filename}"/>
				<a id="specialNeedDocumentDownloadLink" href="${pageContext.request.contextPath}/specialNeed/retrieveFile.html?document=${specialNeedAssociableDocument.document.id}" class="downloadLink ${displayClass}"> 
				<fmt:message key="titleExtensionFormat" bundle="${documentBundle}">
					<fmt:formatDate value="${specialNeedAssociableDocument.document.date}" pattern="MM/dd/yyyy" var="documentDate"/>
					<fmt:param value="${filename}"/><fmt:param value="${documentDate}"/>
				</fmt:message>
				</a>
	 		</span>
 		</span>
 	</fieldset>
	<fieldset id="specialNeedNotesHolder">
		<legend><fmt:message key="specialNeedNotesTitle"/></legend>
			<jsp:include page="specialNeedNotesContent.jsp"/>
		<form:errors path="specialNeedNotes" cssClass="error"/>
	</fieldset>
		<c:if test="${not empty specialNeed}">
			<c:set var="updatable" value="${specialNeed}" scope="request"/>
			<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
		</c:if>
		<p class="buttons">
			<input type="submit" value="<fmt:message key='specialNeedSaveLabel'/>"/>
		</p>
</form:form>
</fmt:bundle>