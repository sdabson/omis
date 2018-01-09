<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<fmt:bundle basename="omis.offenderflag.msgs.offenderFlag">
	<head>		
		<title>
			<fmt:message key="offenderFlagCategoryTitle"/>				
		</title>
		<jsp:include page="/WEB-INF/views/common/includes/headerGeneralResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/searchResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/formResources.jsp"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/scripts/form.js?VERSION=1"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/offenderFlag/offenderFlagCategory/scripts/offenderFlagCategories.js?VERSION=1"></script>
	</head>
	<body>
		<h1>
			<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/offenderFlagCategory/offenderFlagCategoryActionMenu.html"></a>
					<fmt:message key="offenderFlagCategoryTitle"/>
		</h1>		
		<jsp:include page="includes/offenderFlagCategoryForm.jsp"/>
	</body>
	</fmt:bundle>
</html>