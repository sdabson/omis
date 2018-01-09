<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<fmt:bundle basename="omis.physicalfeature.msgs.physicalfeature">
		<head>
			<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
			<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
		   	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/physicalFeature/scripts/physicalFeatureAssociation.js"> </script>
		   	<script type="text/javascript">
			  /* <![CDATA[ */
					//Track current physical feature photo index
					var currentPhysicalFeaturePhotoIndex = ${currentPhysicalFeaturePhotoIndex};
					var currentPhysicalFeatureAssociationNoteItemIndex = ${currentPhysicalFeatureAssociationNoteItemIndex};
			  /* ]]> */
  			</script>
		   	<title>
		   		<c:choose>
					<c:when test="${not empty physicalFeatureAssociation}">
						<fmt:message key="offenderPhysicalFeatureEditTitle">
						</fmt:message>
						<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
					</c:when>
					<c:otherwise>
						<fmt:message key="offenderPhysicalFeatureCreateTitle">
						</fmt:message>
						<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
					</c:otherwise>
				</c:choose>
		   	</title>
		</head>
		<body>
			<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
			<h1>
				<a class="actionMenuItem" id="physicalFeatureAssociationActionMenuLink" href="${pageContext.request.contextPath}/physicalFeature/physicalFeatureAssociaitonActionMenu.html?offender=${offender.id}"></a>
				<c:choose>
					<c:when test="${not empty physicalFeatureAssociation}">
						<fmt:message key="offenderPhysicalFeatureEditTitle">
						</fmt:message>
					</c:when>
					<c:otherwise>
						<fmt:message key="offenderPhysicalFeatureCreateTitle">
						</fmt:message>
					</c:otherwise>
				</c:choose>
			</h1>
			<jsp:include page="includes/physicalFeatureAssociationForm.jsp"/>
		</body>
	</fmt:bundle>
</html>