<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<fmt:bundle basename="omis.offenderflag.msgs.offenderFlag">
		<head>
			<jsp:include page="/WEB-INF/views/common/includes/headerGeneralResources.jsp"/>
			<jsp:include page="/WEB-INF/views/common/includes/headerListResources.jsp"/>
			<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
			<script type="text/javascript" src="${pageContext.request.contextPath}/resources/offenderFlag/offenderFlagCategory/scripts/offenderFlagCategories.js?VERSION=1"></script>
			<title>
				<fmt:message key="offenderFlagCategoryTitle"/>
			</title>
		</head>
		 <body> 
			 <h1>	
			 	<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/offenderFlagCategory/offenderFlagCategoriesActionMenu.html"></a><fmt:message key="offenderFlagCategoryHeader"/>		
			 </h1>
			<jsp:include page="includes/listTable.jsp"/> 
		</body>
	</fmt:bundle>
</html>