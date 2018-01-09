<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.identificationnumber.msgs.identificationNumberCategory">
<head>
	<title>
		<c:choose>
			<c:when test="${not empty identificationNumberCategory}">
				<fmt:message key="identificationNumberCategoryEditTitle"/>
			</c:when>
			<c:otherwise>
				<fmt:message key="identificationNumberCategoryCreateTitle"/>
			</c:otherwise>
		</c:choose>
	</title>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/identificationNumber/scripts/identificationNumberCategory.js?VERSION=1"></script>
</head>
<body>
	<h1>
		<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/identificationNumber/category/identificationNumberCategoryActionMenu.html"></a>
		<c:choose>
			<c:when test="${not empty identificationNumberCategory}">
				<fmt:message key="identificationNumberCategoryEditTitle"/>
			</c:when>
			<c:otherwise>
				<fmt:message key="identificationNumberCategoryCreateTitle"/>
			</c:otherwise>
		</c:choose>
	</h1>
	<jsp:include page="includes/editForm.jsp"/>
</body>
</fmt:bundle>
</html>