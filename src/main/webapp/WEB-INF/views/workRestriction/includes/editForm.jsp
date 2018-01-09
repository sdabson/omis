<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<sec:authorize var="editworkRestriction" access="hasRole('WORK_RESTRICTION_CREATE') or hasRole('ADMIN')"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.workrestriction.msgs.workRestriction">
<form:form commandName="workRestrictionForm" class="editForm">
	<fieldset>
		<legend><fmt:message key="workRestrictionTitle"/></legend>
		<span class="fieldGroup">
			<form:label path="category" class="fieldLabel">
				<fmt:message key="categoryLabel"/>
			</form:label>
			<form:select path="category">
				<jsp:include page="../../includes/nullOption.jsp"/>
				<c:forEach items="${categories}" var="category">
					<c:set var="categoryName" value="${category.name}"/>
					<option value="${categoryName}" ${categoryName == workRestrictionForm.category ? 'selected="selected"' : ''}>
						<c:out value="${categoryName}"/>
					</option>
				</c:forEach>
			</form:select>
			<form:errors path="category" cssClass="error"/>
		</span>
		
		<span class="fieldGroup">
			<label class="fieldLabel" for="authorizedByUser"><fmt:message key="authorizedByLabel"/></label>
			<c:choose>
				<c:when test="${not empty authorizedByQuery}"><c:set var="authorizedByQuery" value="${authorizedByQuery}"/></c:when>
				<c:when test="${not empty workRestrictionForm.authorizedByUser}"><c:set var="authorizedByQuery"><c:set var="userAccount" value="${workRestrictionForm.authorizedByUser}" scope="request"/>
					<jsp:include page="/WEB-INF/views/user/includes/userAccount.jsp"/></c:set>
				</c:when>
			</c:choose>
			<input type="text" name="authorizedByQuery" id="authorizedByQuery" value="<c:out value='${authorizedByQuery}'/>"/>
			<input type="hidden" name="authorizedByUser" id="authorizedByUser" value="<c:out value='${workRestrictionForm.authorizedByUser.id}'/>"/>
			<form:errors path="authorizedByUser" cssClass="error"/>
			<a id="clearAuthorizedByUserLink" class="clearLink" title="<fmt:message key='clearAuthorizedByLink' />" href="${pageContext.request.contextPath}/workRestriction/clearAuthorizedByUser.html?workRestriction=${workRestriction.id}"></a>
			<a id="useCurrentUserAccountLink" class="currentUserAccountLink" title="<fmt:message key='useCurrentUserAccountAsAuthorizedByUserLink' />" href="${pageContext.request.contextPath}/workRestriction/userCurrentUserAccountAsAuthorizedByUser.html?workRestriction=${workRestriction.id}"></a>
		</span>
		
		<span class="fieldGroup">
			<form:label path="authorizationDate" class="fieldLabel">
				<fmt:message key="authorizationDateLabel"/>
			</form:label>
			<form:input path="authorizationDate" class="date"/>
			<form:errors path="authorizationDate" cssClass="error"/>
		</span>
		
		<span class="fieldGroup">
			<form:label path="notes" class="fieldLabel">
				<fmt:message key="commentsLabel"/>
			</form:label>
			<form:textarea path="notes" />
		</span>
		
	</fieldset>
	
	
	<!--  notes -->
	<fieldset>
		<span class="fieldGroup">
			<c:set var="noteItems" value="${workRestrictionForm.noteItems}" scope="request"/>
			<jsp:include page="noteTable.jsp"/>
		</span>
	</fieldset>
	
	
	<c:if test="${not empty workRestriction}">
		<c:set var="updatable" value="${workRestriction}" scope="request"/>
		<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
	</c:if>
	<c:if test="${editworkRestriction}">
		<p class="buttons">
			<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
		</p>
	</c:if>
</form:form>
</fmt:bundle>