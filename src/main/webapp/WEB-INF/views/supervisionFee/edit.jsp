<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.supervisionfee.msgs.supervisionFee">
<head>
	<title>
		<c:choose>
			<c:when test="${not empty monthlySupervisionFee}">
				<fmt:message key="editSupervisionFeeTitle"/>
			</c:when>
			<c:otherwise>
				<fmt:message key="createSupervisionFeeTitle"/>
			</c:otherwise>
		</c:choose>
		<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
	</title>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/searchResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/supervisionFee/style/supervisionFee.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/supervisionFee/scripts/JQuery/jquery.omis.supervisionFee.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/supervisionFee/scripts/supervisionFee.js?VERSION=1"> </script>
	<script type="text/javascript">
	  /* <![CDATA[ */
		var currentFeeRequirementIndex = ${currentFeeRequirementIndex};
	 /* ]]> */
	</script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>	
	<h1>		
		<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/supervisionFee/supervisionFeeActionMenu.html?offender=${offender.id}"></a>
		<c:choose>
			<c:when test="${not empty monthlySupervisionFee}">
				<fmt:message key="editSupervisionFeeTitle"/>
			</c:when>
			<c:otherwise>
				<fmt:message key="createSupervisionFeeTitle"/>
			</c:otherwise>
		</c:choose>		
	</h1>
	<jsp:include page="includes/editForm.jsp"/>
</body>	
</fmt:bundle>
</html>