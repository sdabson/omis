<?xml version="1.0" encoding="UTF-8"?>
<%-- Author: Ryan Johns
 - Version: 0.1.0 (Feb 10, 2015)
 - Since: OMIS 3.0 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<ul class="toolbar">
	<li>
		<a id="treatmentActionMenuLink" class="actionMenuItem" href="${pageContext.request.contextPath}/placementScreening/referral/treatmentListActionMenu.html"></a>
	</li>
</ul>
<table id="treatmentList" class="editTable">
	<c:set value="${placementReferralForm.treatmentScreeningItems}" var="screeningItems" scope="request"/>
	<jsp:include page="treatmentTableBody.jsp"/>
</table>