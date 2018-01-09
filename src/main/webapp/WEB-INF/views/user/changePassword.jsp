<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Author: Stephen Abson
 - Version: 0.1.0 ({date})
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.user.msgs.changePassword">
<head>
	<title><fmt:message key="changePasswordTitle"/></title>
	<jsp:include page="/WEB-INF/views/common/includes/headerMetas.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/headerGeneralResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/formResources.jsp"/>
</head>
<body>
<h1><fmt:message key="changePasswordTitle"/></h1>
<form:form commandName="passwordChangeForm" class="editForm">
	<fieldset>
		<span class="fieldGroup">
		<form:label path="oldPassword" class="fieldLabel">
			<fmt:message key="oldPasswordLabel"/></form:label>
		<form:password path="oldPassword" />
		<form:errors path="oldPassword" cssClass="error"/>
		</span>
		<span class="fieldGroup">
		<form:label path="newPassword" class="fieldLabel">
			<fmt:message key="newPasswordLabel"/></form:label>
		<form:password path="newPassword"/>
		<form:errors path="newPassword" cssClass="error"/>
		</span>
		<span class="fieldGroup">
		<form:label path="confirmedNewPassword" class="fieldLabel">
			<fmt:message key="confirmedNewPasswordLabel"/></form:label>
		<form:password path="confirmedNewPassword"/>
		<form:errors path="confirmedNewPassword" cssClass="error"/>
		</span>
	</fieldset>
	<p class="buttons">
		<input type="submit" value="<fmt:message key='okLabel'/>"/>
	</p>
</form:form>
</body>
</fmt:bundle>
</html>