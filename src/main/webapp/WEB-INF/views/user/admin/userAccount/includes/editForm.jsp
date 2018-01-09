<%--
 - Form to edit user accounts.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="omis.msgs.common" var="common"/>
<fmt:bundle basename="omis.user.msgs.userAccount">
<fmt:setBundle basename="omis.person.msgs.name" var="nameBundle"/>
<form:form commandName="userAccountForm" class="editForm">
	<c:if test="${empty user}">
	<fieldset>
		<legend><fmt:message key="nameLabel" bundle="${nameBundle}"/></legend>
		<jsp:include page="/WEB-INF/views/person/name/includes/nameFields.jsp"/>
		<form:hidden path="allowUser"/>
	</fieldset>
	</c:if>
	<fieldset>
		<legend>
		<c:choose>
		<c:when test="${not empty user}">
			<fmt:message key="userAccountWithUserNameLabel">
				<fmt:param>
					<c:out value="${user.name.lastName}"/>, <c:out value="${user.name.firstName}"/>
				</fmt:param>
			</fmt:message>
		</c:when>
			<c:otherwise>
				<fmt:message key="userAccountLabel"/>
			</c:otherwise>
		</c:choose>
		</legend>
		<span class="fieldGroup">
		<form:label path="username" class="fieldLabel">
				<fmt:message key="usernameLabel"></fmt:message>
			</form:label>
		<form:input path="username" readonly="${not empty userAccount}"/>
		<form:errors path="username" cssClass="error"/>
		<%-- <form:checkbox path="enabled"/>
		<form:label path="enabled" class="fieldValueLabel">
			<fmt:message key="enabledLabel"/></form:label>
		<form:errors path="enabled" cssClass="error"/> --%>
		</span>
		<c:if test="${userAccountForm.allowPassword}">
			<span class="fieldGroup">
			<form:label path="newPassword" class="fieldLabel">
				<fmt:message key="newPasswordLabel"/></form:label>
			<form:password path="newPassword"/>
			<form:errors path="newPassword" cssClass="error"/>
			</span>
			<span class="fieldGroup">
			<form:label path="confirmPassword" class="fieldLabel">
				<fmt:message key="confirmPasswordLabel"/></form:label>
			<form:password path="confirmPassword"/>
			<form:errors path="confirmPassword"  cssClass="error"/>
			</span>
			<span class="fieldGroup">
			<form:label path="passwordExpirationDate" class="fieldLabel">
				<fmt:message key="passwordExpirationDateLabel"/></form:label>
			<form:input class="date" path="passwordExpirationDate" id="passwordExpirationDate" disabled="${userAccountForm.passwordNeverExpires}"/>
			<form:checkbox path="passwordNeverExpires" id="passwordNeverExpires"/>
			<form:label path="passwordNeverExpires">
				<fmt:message key="passwordNeverExpiresLabel"/></form:label>
			<form:errors path="passwordExpirationDate" cssClass="error"/>
			</span>
		</c:if>
	</fieldset>
	<c:if test="${userAccountForm.allowGroups}">
		<fieldset id="groups">
			<legend><fmt:message key="groupsLabel"/></legend>
			<span id="groups" class="fieldGroup">
			<form:checkboxes items="${groups}" path="groups" class="fieldValue groups"
			                 itemValue="id" itemLabel="name"/>
			</span>
			<span id="roles" class="fieldGroup">
			<label class="fieldLabel"><fmt:message key="rolesLabel"/></label>
			<span id="roleSpans">
			<jsp:include page="/WEB-INF/views/user/admin/userRole/includes/listSpans.jsp"/>
			</span>
			</span>
		</fieldset>
	</c:if>
	<c:if test="${not empty userAccount and not empty accessAttempts}">
		<h5><fmt:message key="accessAttemptsLabel"/></h5>
		<div id="accessAttemptsContainer">
			<jsp:include page="/WEB-INF/views/security/accessAttempt/includes/listTable.jsp"/>
		</div>
	</c:if>
	<p class="buttons">
		<input type="submit" value="<fmt:message key='saveLabel' bundle='${common}'/>"/>
	</p>
</form:form>
</fmt:bundle>