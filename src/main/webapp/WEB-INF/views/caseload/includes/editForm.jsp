<%-- Author: Ryan Johns
 - Version: 0.1.0 (Jun 15, 2017)
 - Since: OMIS 3.0 --%>
 <%@ page trimDirectiveWhitespaces="true" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
 <fmt:bundle basename="omis.caseload.msgs.caseload">
 <form:form commandName="editForm" class="editForm">
 <fieldset>
 	<legend><fmt:message key="caseloadDetailsTitle"/></legend>
 	<span class="fieldGroup">
 		<form:label path="caseloadName" class="fieldLabel">
 			<fmt:message key="caseloadNameLabel"/>
 		</form:label>
 		<form:input path="caseloadName"/>
 		<form:errors path="caseloadName" cssClass="error"/>
 	</span>
 	<span class="fieldGroup">
 		<c:if test="${not empty editForm.userAccount}">
 			<c:set value="${editForm.userAccount.user.name}" var="name"/>
 			<c:set value="${name.lastName},${not empty name.middleName? ' ' + name.middleName + ' ': ''} ${name.firstName}" var="userAccountPersonFullName"/>
 			<c:set value="${editForm.userAccount.username}" var="username"/>
 		</c:if>
	 	<form:label path="userAccount" class="fieldLabel">
	 		<fmt:message key="userAccountLabel"/>
	 	</form:label>
	 	<input id="userAccountInput" value="${userAccountPersonFullName} (${username})" class="userAccount_autocomplete"/>
	 	<form:hidden  id="userAccount" path="userAccount"/>
	 	<form:errors path="userAccount" cassClass="error"/>
 	</span> 
 	<span class="fieldGroup">
 		<form:label path="startDate" class="fieldLabel">
 			<fmt:message key="startDate"/>
 		</form:label>
 		<form:input id="startDate" path="startDate" class="date"/>
 		<form:errors path="startDate" cssClass="error"/>
 	</span>
 	<span class="fieldGroup">
 		<form:label path="endDate" class="fieldLabel">
 			<fmt:message key="endDate"/>
 		</form:label>
 		<form:input id="endDate" path="endDate" class="date"/>
 		<form:errors path="endDate" cssClass="error"/>
 	</span>
 	<span class="fieldGroup">
 		<form:label path="caseworkCategory" class="fieldLabel">
 			<fmt:message key="caseworkCategoryLabel"/>
 		</form:label>
 		<form:select path="caseworkCategory">
 			<fmt:message key="nullLabel" var="nullLabel" bundle="${commonBundle}"/>
 			<form:option value="" label="${nullLabel}"/>
 			<c:forEach items="${caseworkCategories}" var="category">
 				<fmt:message key="caseworkCategory.${category.name}Label" var="label"/>
 				<form:option value="${category.name}" label="${label}"/>
 			</c:forEach>
 		</form:select>
 		<form:errors path="category" cssClass="error"/>
 	</span>
 	<c:if test="${not empty caseload}">
 		<c:set var="updateable" value="${caseload}" scope="request"/>
 		<jsp:include page="/WEB-INF/views/audit/includes/updateSignature.jsp"/> 
 	</c:if>
 	<p class="buttons">
		<input type="submit" value="<fmt:message key='saveLabel' bundle='${commonBundle}'/>"/>
	</p>
 </fieldset>
 </form:form>
 </fmt:bundle>