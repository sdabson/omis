<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<fmt:bundle basename="omis.physicalfeature.msgs.otherphoto">
		<head>
			<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
			<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
			<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/otherPhoto/style/otherPhotoWizard.css"/>
			<jsp:include page="/WEB-INF/views/common/includes/jQueryResources.jsp"/>
			<script type="text/javascript" src="${pageContext.request.contextPath}/resources/otherPhoto/scripts/otherPhoto.js"> </script>
			<title>
				<fmt:message key="otherPhysicalFeaturePhotoAssociationLabel"/>
				<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
			</title>
		</head>
		<body>
			<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
			<h1>
				<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/otherPhoto/displayOtherPhotoWizardActionMenu.html?offender=${offender.id}"></a>
				<fmt:message key="otherPhysicalFeaturePhotoAssociationLabel"/>
			</h1>
			
			<div id="otherPhotosFormContent">
				<jsp:include page="includes/otherPhotoForm.jsp"/>
			</div>
		</body>
	</fmt:bundle>
</html>
