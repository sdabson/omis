<!-- 
 - Author: Annie Jacques
 - Version: 0.1.0 (July 27, 2016)
 - Since: OMIS 3.0
 -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<sec:authorize var="editEducation" access="hasRole('EDUCATION_EDIT') or hasRole('ADMIN') or hasRole('EDUCATION_CREATE')"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.education.msgs.education">
<form:form commandName="educationForm" class="editForm">
	<fieldset>
		<legend><fmt:message key="attendedTitle"/></legend>
		<span class="fieldGroup">
			<form:label path="attendedStartDate" class="fieldLabel">
				<fmt:message key="attendedStartDateLabel"/>
			</form:label>
			<form:input path="attendedStartDate" class="date"/>
			<form:errors path="attendedStartDate" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="attendedEndDate" class="fieldLabel">
				<fmt:message key="attendedEndDateLabel"/>
			</form:label>
			<form:input path="attendedEndDate" class="date"/>
			<form:errors path="attendedEndDate" cssClass="error"/>
		</span>
	</fieldset>
	
	<fieldset>
		<legend><fmt:message key="instituteTitle"/></legend>
		<span class="fieldGroup">
			<form:label path="institute.category" class="fieldLabel">
				<fmt:message key="instituteCategoryLabel"/>
			</form:label>
			<form:select path="institute.category">
				<jsp:include page="../../includes/nullOption.jsp"/>
				<c:forEach items="${instituteCategories}" var="instituteCategory">
					<c:set var="instituteCategoryId" value="${instituteCategory.id}"/>
					<option value="${instituteCategoryId}" ${instituteCategoryId == educationForm.institute.category.id ? 'selected="selected"' : ''}>
						<c:out value="${instituteCategory.name}"/>
					</option>
				</c:forEach>
			</form:select>
		</span>
		<span class="fieldGroup">
			<form:label path="institute.name" class="fieldLabel">
				<fmt:message key="instituteNameLabel"/>
			</form:label>
			<form:input path="institute.name"/>
			<form:errors path="institute" cssClass="error"/>
		</span>
		
	</fieldset> 
	<fieldset>
		<legend><fmt:message key="descriptionTitle"/></legend>
		<!-- Description -->
		<span class="fieldGroup">
			<form:label path="description" class="fieldLabel">
				<fmt:message key="descriptionLabel"/>
			</form:label>
			<form:textarea path="description" />
		</span>
		
		<!-- Special education -->
		<span class="fieldGroup">
			<form:label path="specialEducation" class="fieldLabel">
				<fmt:message key="specialEducationLabel"/>
			</form:label>
			<form:checkbox path="specialEducation" />
		</span>
	</fieldset>
	<fieldset><!-- achievement -->
		<legend><fmt:message key="graduationTitle"/></legend>
		<span class="fieldGroup">
			<form:label path="graduated" class="fieldLabel">
				<fmt:message key="graduatedLabel"/>
			</form:label>
			<form:checkbox path="graduated" />
		</span>
		<span class="fieldGroup">
			<form:label path="achievementDate" class="fieldLabel">
				<fmt:message key="achievementDateLabel"/>
			</form:label>
			<form:input path="achievementDate" class="date"/>
			<form:errors path="achievementDate" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="achievementDescription" class="fieldLabel">
				<fmt:message key="achievementDescriptionLabel"/>
			</form:label>
			<form:textarea path="achievementDescription" />
			<form:errors path="achievementDescription" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="achievementCategory" class="fieldLabel">
				<fmt:message key="achievementCategoryLabel"/>
			</form:label>
			<form:select path="achievementCategory">
				<jsp:include page="../../includes/nullOption.jsp"/>
				<c:forEach items="${achievementCategories}" var="achievementCategory">
					<c:set var="achievementCategoryId" value="${achievementCategory.id}"/>
					<option value="${achievementCategoryId}" ${achievementCategoryId == educationForm.achievementCategory.id ? 'selected="selected"' : ''}>
						<c:out value="${achievementCategory.name}"/>
					</option>
				</c:forEach>
			</form:select>
			<form:errors path="achievementCategory" cssClass="error"/>
		</span>
	</fieldset>
	
	<!-- Verification -->
	<fieldset>
		<legend><fmt:message key="verificationTitle"/></legend>
		<span class="fieldGroup">
			<form:label path="verified" class="fieldLabel">
				<fmt:message key="verifiedLabel"/>
			</form:label>
			<form:checkbox path="verified" />
		</span>
		<span class="fieldGroup">
			<form:label path="verifiedUser" class="fieldLabel">
				<fmt:message key="userLabel"/>
			</form:label>
			<input id="userInput"/>
				<form:hidden id="user" path="verifiedUser"/>
				<a id="currentUser" class="currentUserAccountLink"></a>
				<a id="clearUser" class="clearLink"></a>
			<span id="userDisplay">
				<c:if test="${not empty educationTerm.verificationSignature.userAccount}" >
					<c:out value="${educationTerm.verificationSignature.userAccount.user.name.lastName}, 
								${educationTerm.verificationSignature.userAccount.user.name.firstName} (${educationTerm.verificationSignature.userAccount.username})"/>
				</c:if>
			</span>
			<form:errors path="verifiedUser" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="verifiedDate" class="fieldLabel">
				<fmt:message key="verifiedDateLabel"/>
			</form:label>
			<form:input path="verifiedDate" class="date"/>
			<form:errors path="verifiedDate" cssClass="error"/>
		</span>
		<span class="fieldGroup">
			<form:label path="verificationMethod" class="fieldLabel">
				<fmt:message key="verificationMethodLabel"/>
			</form:label>
			<form:select path="verificationMethod">
				<jsp:include page="../../includes/nullOption.jsp"/>
				<c:forEach items="${verificationMethods}" var="verificationMethod">
					<c:set var="verificationMethodId" value="${verificationMethod.id}"/>
					<option value="${verificationMethodId}" ${verificationMethodId == educationForm.verificationMethod.id ? 'selected="selected"' : ''}>
						<c:out value="${verificationMethod.name}"/>
					</option>
				</c:forEach>
			</form:select>
			<form:errors path="verificationMethod" cssClass="error"/>
		</span>
	</fieldset>
	
	<!-- achievements -->
	<fieldset>
		<legend><fmt:message key="achievementsTitle"/></legend>
		<span class="fieldGroup">
			<c:set var="achievementItems" value="${educationForm.achievementItems}" scope="request"/>
			<jsp:include page="achievementTable.jsp"/>
		</span>
	</fieldset>
	
	<!--  notes -->
	<fieldset>
		<legend><fmt:message key="notesTitle"/></legend>
		<span class="fieldGroup">
			<c:set var="noteItems" value="${educationForm.noteItems}" scope="request"/>
			<jsp:include page="noteTable.jsp"/>
		</span>
	</fieldset>
	
	<c:if test="${not empty educationTerm}">
		<c:set var="updatable" value="${educationTerm}" scope="request"/>
		<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/>
	</c:if>
	<c:if test="${editEducation}">
		<p class="buttons">
			<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
		</p>
	</c:if>
	
</form:form>
</fmt:bundle>