<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Author: Stephen Abson
 - Version: 0.1.0 (Feb 26, 2014)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.offenderphoto.msgs.offenderPhoto">
<head>
	<title>
		<c:choose>
			<c:when test="${not empty association}">
				<fmt:message key="editOffenderPhotoTitle"/>
				<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
			</c:when>
			<c:otherwise>
				<fmt:message key="createOffenderPhotoTitle"/>
				<jsp:include page="/WEB-INF/views/offender/includes/offenderNameSummary.jsp"/>
			</c:otherwise>
		</c:choose>
	</title>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/jQueryResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/messageResolverResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/serverConfigResources.jsp"/>
	<script type="text/javascript">
				/* <![CDATA[ */
					//offender photo association note item index used to track offender photo association note items on the form 
					var currentOffenderPhotoAssociationNoteItemIndex = ${offenderPhotoAssociationNoteItemIndex};
				/* ]]> */
	</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/offenderPhoto/scripts/jquery/jquery.omis.offenderPhoto.js"> </script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/offenderPhoto/scripts/offenderPhoto.js"> </script>
</head>
<body>	
	<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
	<h1>
		<a class="actionMenuItem" id="actionMenuLink" href="${pageContext.request.contextPath}/offenderPhoto/photoActionMenu.html?offender=${offender.id}"></a>
		<c:choose>
			<c:when test="${not empty association}">
				<fmt:message key="editOffenderPhotoTitle"/>
			</c:when>
			<c:otherwise>
				<fmt:message key="createOffenderPhotoTitle"/>
			</c:otherwise>
		</c:choose>
	</h1>
	<jsp:include page="includes/editForm.jsp"/>
</body>
</fmt:bundle>
</html>