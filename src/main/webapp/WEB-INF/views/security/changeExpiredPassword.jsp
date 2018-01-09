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
	<style type="text/css">
	/* <![CDATA[ */
	body {
		margin-top: 40px;
		background-color: #D1D1D1;
	}
	form#changePasswordForm table {
		background-color: #FFFFFF;
	}
	form#changePasswordForm table {
		margin-left: auto;
		margin-right: auto;
		border: 1px solid;
		padding: 16px;
	}
	h2 {
		margin: 0px;
		padding: 0px;
		text-align: center;
	}
	h3 {
		margin: 0px;
		padding: 0px;
	}
	input[type=text], input[type=password] {
		width: 100px;
	}
	input[type=submit], input[type=reset] {
		width: 80px;
	}
	td.buttons, td.message, td.links {
		text-align: center;
	}
	#disclaimer {
		width: 620px;
		margin-left: auto;
		margin-right: auto;
		border: solid black 1px;
		background-color: lightgray;
		padding: 5px;
	}
	label {
		text-align: right;
	}
	span.error {
	  color: red;
	}
	/* ]]> */
	</style>
</head>
<body>
<form:form commandName="passwordChangeForm" id="changePasswordForm">
	<table>
		<tr><td colspan="3"><h2><fmt:message key="expiredPasswordTitle"/></h2></td></tr>
		<tr><td colspan="3" class="message"><fmt:message key="expiredPasswordMessage"/></td></tr>
		<tr>
		<td><form:label path="oldPassword" class="fieldLabel">
			<fmt:message key="oldPasswordLabel"/></form:label></td>
		<td><form:password path="oldPassword" /></td>
		<td><form:errors path="oldPassword" cssClass="error"/></td>
		</tr>
		<tr>
		<td><form:label path="newPassword" class="fieldLabel">
			<fmt:message key="newPasswordLabel"/></form:label></td>
		<td><form:password path="newPassword"/></td>
		<td><form:errors path="newPassword" cssClass="error"/></td>
		</tr>
		<tr>
		<td><form:label path="confirmedNewPassword" class="fieldLabel">
			<fmt:message key="confirmedNewPasswordLabel"/></form:label></td>
		<td><form:password path="confirmedNewPassword"/></td>
		<td><form:errors path="confirmedNewPassword" cssClass="error"/></td>
		</tr>
		<tr><td colspan="3" class="links"><a href="${pageContext.request.contextPath}/login.html"><fmt:message key="backToLoginLink"/></a></td></tr>
		<tr><td colspan="3" class="buttons"><input type="submit" value="<fmt:message key='okLabel'/>"/></td></tr>
	</table>
</form:form>
</body>
</fmt:bundle>
</html>