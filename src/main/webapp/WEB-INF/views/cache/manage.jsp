<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.cache.msgs.cache">
<head>
	<title><fmt:message key="cacheManagerTitle"/></title>
	<jsp:include page="/WEB-INF/views/common/includes/headerMetas.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/headerGeneralResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/serverConfigResources.jsp"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/cache/scripts/manage.js"> </script>
</head>
<body>
	<h1><fmt:message key="cacheManagerTitle"/></h1>
	<p style="font-size: 14px; font-weight: bold; border: darkred 2px solid; background-color: lightgrey; color: darkred; padding: 2px;">
		Experimental
	</p>
	<form>
		<fieldset>
			<legend><fmt:message key="secondLevelCacheLabel"/></legend>
			<p>
			<label for="regionName"><fmt:message key="regionsLabel"/></label>
			<select id="regionName" name="regionName">
			  <option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
			  <c:forEach var="regionName" items="${regionNames}">
			    <option value="${regionName}"><c:out value="${regionName}"/></option>
			  </c:forEach>
			</select>
			<button id="clearRegion" type="submit"><fmt:message key="clearRegionLabel"/></button>
			<span id="clearRegionResult"></span>
			</p>
			<p>
			<label for="entityName"><fmt:message key="entitiesLabel"/></label>
			<select id="entityName" name="entityName">
			  <option value=""><fmt:message key="nullLabel" bundle="${commonBundle}"/></option>
			  <c:forEach var="entityName" items="${entityNames}">
			    <option value="${entityName}"><c:out value="${entityName}"/></option>
			  </c:forEach>
			</select>
			<button id="clearEntity" type="submit"><fmt:message key="clearEntityLabel"/></button>
			<span id="clearEntityResult"></span>
			</p>
			<p>
			<button id="clear" type="submit"><fmt:message key="clearLabel"/></button>
			<span id="clearResult"></span>
			</p>
		</fieldset>
	</form>
</body>
</fmt:bundle>
</html>