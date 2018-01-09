<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Offender profile screen.
 -
 - Author: Stephen Abson
 - Author: Ryan Johns
 - Author: Annie Jacques
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<fmt:bundle basename="omis.offender.msgs.profile">
	<input type="hidden" id="headerOffenderId" value="${offenderSummary.id}" />
	<div id="fullNavBarWrapper">
		<div id="navLeftArrow" class="arrow"></div>
		<div id="navRightArrow" class="arrow"></div>
	<div id="navBarWrapper">
		<div id="navBar" class="noSelect">
			<div class="navBarItem accentDark" id="basicInformationProfile">
				<fmt:message key="basicInformationLabel"/>
			</div>
			<div class="navBarItem accentDark" id="placementProfile">
				<fmt:message key="placementLabel"/>
			</div>
			<div class="navBarItem accentDark" id="legalProfile">
				<fmt:message key="legalLabel"/>
			</div>
			<div class="navBarItem accentDark" id="caseManagementProfile">
				<fmt:message key="caseManagementLabel"/>
			</div>
			<div class="navBarItem accentDark" id="safetyProfile">
				<fmt:message key="safetyLabel"/>
			</div>
			<div class="navBarItem accentDark" id="complianceProfile">
				<fmt:message key="complianceLabel"/>
			</div>
			<div class="navBarItem accentDark" id="healthProfile">
				<fmt:message key="healthLabel"/>
			</div>
			<div class="navBarItem accentDark" id="relationshipsProfile">
				<fmt:message key="relationshipsLabel"/>
			</div>
		</div>
	</div>
	</div>
	<div class="navContentWrapper">
		<div class="navContent accentDark" id="basicInformationProfileContent"></div>
		<div class="navContent accentDark" id="placementProfileContent"></div>
		<div class="navContent accentDark" id="legalProfileContent"></div>
		<div class="navContent accentDark" id="caseManagementProfileContent"></div>
		<div class="navContent accentDark" id="safetyProfileContent"></div>
		<div class="navContent accentDark" id="complianceProfileContent"></div>
		<div class="navContent accentDark" id="healthProfileContent"></div>
		<div class="navContent accentDark" id="relationshipsProfileContent"></div>
	</div>
</fmt:bundle>