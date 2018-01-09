<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.financial.msgs.financial">
<head>
	<title>
		<fmt:message key="financialProfileTitle"/>
		<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
	</title>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<jsp:include page="/WEB-INF/views/document/includes/documentTagResources.jsp"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/scripts/SessionConfig.js"> </script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/financial/scripts/financial.js?VERSION=1"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/financial/scripts/includes/jquery.omis.financialDocumentAssociation.js?VERSION=1"></script>
	<script type="text/javascript">
		var financialAssetItemIndex = ${financialAssetItemIndex};
		var financialLiabilityItemIndex = ${financialLiabilityItemIndex};
		var recurringDeductionItemIndex = ${recurringDeductionItemIndex};
 		var financialDocumentAssociationItemIndex = ${financialDocumentAssociationItemIndex};
 		var currentDocumentTagItemIndexes = [];
		<c:forEach items="${documentTagItemIndexes}" var="id">
			currentDocumentTagItemIndexes.push(parseInt("${id}"));
		</c:forEach>
		
	</script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/financial/style/financial.css?VERSION=1"/>
</head>
<body>
	<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
	<h1>
		<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/financial/financialProfileActionMenu.html?offender=${offender.id}"></a>
		<fmt:message key="financialProfileTitle"/>
	</h1>
	<jsp:include page="includes/editForm.jsp"/>
</body>
</fmt:bundle>
</html>