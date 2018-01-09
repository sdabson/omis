<?xml version="1.0" encoding="UTF-8"?>
<%-- Author: Ryan Johns
 - Version: 0.1.0 (Feb 10, 2015)
 - Since: OMIS 3.0 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<ul class="toolbar">
	<li><a class="actionMenuItem" id="prereleaseActionMenuLink" href="${pageContext.request.contextPath}/placementScreening/referral/prereleaseListActionMenu.html"></a>
</ul>
<table id="preReleaseList" class="editTable">
	<c:set var="screeningItems" value="${placementReferralForm.prereleaseScreeningItems}" scope="request"/>
	<jsp:include page="preReleaseTableBody.jsp"/>
</table>