<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Single screen index.
 -
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tld/omis.tld" prefix="omis" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
                      "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
  <fmt:setBundle basename="omis.msgs.general" var="generalBundle"/>
  <fmt:bundle basename="omis.msgs.index">
  <head>
    <title>
    	<fmt:message key="productName" bundle="${generalBundle}"/>
    	 -
    	<fmt:message key="${hostingEnvPropertyHolder.propertyValue}HostingEnvLabel" bundle="${generalBundle}"/>
    </title>
    <jsp:include page="/WEB-INF/views/common/includes/headerMetas.jsp"/>
    <jsp:include page="/WEB-INF/views/common/includes/headerGeneralResources.jsp"/>
    <jsp:include page="/WEB-INF/views/common/includes/serverConfigResources.jsp"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/single.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/applicationToolbar.css"/>
	<link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/resources/common/images/appIcon.ico" />
	<script src="${pageContext.request.contextPath}/resources/index/scripts/single.js" type="text/javascript"> </script>
  </head>
  <body class="containerPage">
	<ul id="applicationToolbar">
		<sec:authorize access="hasRole('APP_DEV')" var="appDevRole"/>
		<c:choose>
			<c:when test="${appDevRole}">
	    	<li id="developerTools">
          		<label for="urlInput"><fmt:message key="urlLabel"/></label>
          		<input type="text" id="urlInput" readonly="readonly"/>
          		<a id="refreshLink" class="refreshLink iconLink" href="# "title="<fmt:message key='refreshLink'/>"><span class="linkLabel"><fmt:message key="refreshLink"/></span></a>
			</li>
			</c:when>
			<c:otherwise>
			<li id="productName">
				<a id="aboutLink" class="infoLink iconLink" href="${pageContext.request.contextPath}/about.html" title="<fmt:message key='aboutLink'/>"><span class="linkLabel"><fmt:message key="aboutLink"/></span></a>
				<span class="productName"><fmt:message key="productName" bundle="${generalBundle}"/></span>
				<span class="productVersion"><fmt:message key="productVersion" bundle="${generalBundle}"/></span>
			</li>
			</c:otherwise>
		</c:choose>
        <li id="user">
          <sec:authorize access="hasRole('ADMIN')">
            <a id="adminIndexLink" class="adminIndexLink iconLink" href="${pageContext.request.contextPath}/admin/index.html" title="<fmt:message key='appAdminLink'/>"><span class="linkLabel"><fmt:message key="appAdminLink"/></span></a>
          </sec:authorize>
    	  <a id="homeLink" class="homeLink iconLink" href="${pageContext.request.contextPath}/home.html" title="<fmt:message key='homeLink'/>"><span class="linkLabel"><fmt:message key="homeLink"/></span></a>
    	  <sec:authorize access="hasRole('ADMIN') or hasRole('CHANGE_PASSWORD')">
    		<a id="changePasswordLink" class="changePasswordLink iconLink" href="${pageContext.request.contextPath}/user/changePassword.html" title="<fmt:message key='changePasswordLink'/>"><span class="linkLabel"><fmt:message key="changePasswordLink"/></span></a>
    	  </sec:authorize>
    	  <span class="userDetails">${userAccount.user.name.lastName}, ${userAccount.user.name.firstName} (<a href="${pageContext.request.contextPath}/j_spring_security_logout"><fmt:message key="logoutLink"/></a>)</span>
        </li>
    </ul>
    <div id="contentFrameContainer">
    	<iframe id="contentFrame" src="${pageContext.request.contextPath}/home.html" frameborder="0"> </iframe>
    </div>
  </body>
  </fmt:bundle>
</html>