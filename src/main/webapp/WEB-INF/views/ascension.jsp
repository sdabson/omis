<?xml version="1.0" encoding="UTF-8"?>
<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
  <fmt:setBundle basename="omis.msgs.general" var="general"/>
  <fmt:bundle basename="omis.msgs.index">
  <head>  
    <title><fmt:message key="productName" bundle="${general}"/></title>
	<meta http-equiv="content-type" content="text/html; charset=ISO-8859-1"/>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/general.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/colors.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/fonts.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/application/style/layout.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/jquery/ui/jquery-ui.custom.css"/>
		
		<script src="${pageContext.request.contextPath}/resources/3rdparty/JQuery/jquery.min.js"></script>
		<script src="${pageContext.request.contextPath}/resources/3rdparty/JQuery/ui/jquery-ui-custom.min.js" type="text/javascript"></script>
  	    <script src="${pageContext.request.contextPath}/resources/common/scripts/MessageResolver.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/resources/common/scripts/ServerConfig.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/resources/common/scripts/JQuery/jquery.omis.lookup.offenderSearch.js"></script>
		<script src="${pageContext.request.contextPath}/resources/common/scripts/JQuery/jquery.omis.lookup.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/resources/common/scripts/navigationItem.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/resources/common/scripts/QueueMatrix.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/resources/common/scripts/JQuery/jquery.omis.navigation.cartographer.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/resources/common/scripts/EventRunner.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/resources/application/scripts/ascension.js" type="text/javascript"></script>
  		<style>
  			.navigationLaunch {
  				right: 0px;
  				position: absolute;
  			}
  		</style>
  </head>
  <body>
	<div id="applicationToolBar" class="panel horizontalPanel"> </div>
	<div id="workArea" class="panel horizontalPanel">
		<div id="content" class="panel verticalPanel"></div>
		<div id="leftPanel" class="panel verticalPanel">
			<div id="leftPanelContent" class="panelContent">
				<ul>
					<li>A</li>
					<li>B</li>
				</ul>
			</div>
			<div id="leftPanelLinks" class="panelLinks">
				<ul>
					<li><a href="#" class="leftPanelLink">1</a></li>
					<li><a href="#" class="leftPanelLink">2</a></li>
				</ul>
			</div>
		</div>
		
		<div id="rightPanel" class="panel verticalPanel">
			<div id="rightPanelContent" class="panelContent">
				<ul>
					<li>C</li>
					<li>D</li>
				</ul>
			</div>
			<div id="rightPanelLinks" class="panelLinks">
				<ul>
					<li><a href="#" class="rightPanelLink">3</a></li>
					<li><a href="#" class="rightPanelLink">4</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div id="navigationBar" class="panel horizontalPanel">
		<div id="applicationLaunch">
			<img id="applicationLaunchIcon" src="${pageContext.request.contextPath}/resources/common/images/phoenix.png" height="42px" width="42px"/>
		</div>
		<div id="navigationPanel">
			<div id="navigationMap">
				<ul id="navigationItems">
				</ul>
			</div>
		</div>
		<div id="navigationLaunch">
			<img id="navigationLaunchIcon" src="${pageContext.request.contextPath}/resources/common/images/frogPrint.png" height="42px" width="42px"/>
		</div>
	</div>
	<div id="launchMenu">
		<div id="launchArea">
		</div>
		<div id="search">
			<p>
				<input id="searchCriteria" name="searchCriteria" type="text" class="defaultDisplayText" />
			</p>	
		</div>
	</div>
	<div id="navigationMenu">
		
		
			<ul id="navigationMenuContentItems" class="navigationMenuItems"></ul>
			<ul id="navigationMenuHistoricContentItems" class="navigationMenuItems"></ul>
		
	</div>
			
	</body>
  </fmt:bundle>
</html>
