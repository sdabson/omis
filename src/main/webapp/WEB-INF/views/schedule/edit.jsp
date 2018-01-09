<%--
 - Author: Stephen Abson
 - Version: 0.1.0 (Oct 30, 2013)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html 
     PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
     "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Event</title>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/general.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/style.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/schedule/style/events.css"/>
</head>
<body>
	<h3>Edit Event</h3>
	<form:form commandName="event">
	<table>
		<tr>
			<td>Start Date</td>
			<td><form:input path="dateRange.startDate"/></td>
		</tr>
		<tr>
			<td>End Date:</td>
			<td><form:input path="dateRange.endDate"/></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="Save"/>
				<input type="reset" value="Reset"/>
			</td>
		</tr>
	</table>
	<form:hidden path="id" />
	</form:form>
	<div>
	<hr/>
	<form action="${pageContext.request.contextPath}/schedule/events.html"
			method="get" style="display:inline">
		<p style="display:inline">
		<input type="submit" value="Back"/>
		<input type="hidden" name="viewTypeCode" value="${viewTypeCode}"/>
		</p>
	</form>
		<p style="display:inline">schedule/edit.jsp</p>
	</div>
</body>
</html>