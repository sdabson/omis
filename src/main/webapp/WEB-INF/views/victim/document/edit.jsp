<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Author: Joel Norris
 - Version: 0.1.0 (August 28, 2017)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<fmt:bundle basename="omis.victim.msgs.victim">
		<head>
			<title>
				<fmt:message key="editVictimDocumentsTitle"/>
			</title>
			<jsp:include page="/WEB-INF/views/common/includes/headerOffenderFormResources.jsp"/>
			<jsp:include page="/WEB-INF/views/common/includes/serverConfigResources.jsp"/>
			<jsp:include page="/WEB-INF/views/common/includes/messageResolverResources.jsp"/>
			<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
			<jsp:include page="/WEB-INF/views/common/includes/contactSummaryResources.jsp"/>
			<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/victim/style/victim.css"/>
			<script type="text/javascript" src="${pageContext.request.contextPath}/resources/victim/scripts/jquery/jquery.omis.victimDocuments.js"> </script>
			<script type="text/javascript" src="${pageContext.request.contextPath}/resources/victim/scripts/victimDocuments.js"> </script>
			<script type="text/javascript">
					var currentVictimDocumentItemIndex = ${victimDocumentItemIndex};
					var currentDocumentTagItemIndexes = [];
					var victimId = ${victim.id};
					<c:choose>
						<c:when test="${not empty documentTagItemIndexes}">
							<c:forEach items="${documentTagItemIndexes}" var="id">
								currentDocumentTagItemIndexes.push(parseInt("${id}"));
							</c:forEach>
						</c:when>
						<c:otherwise>
							currentDocumentTagItemIndexes.push(0);
						</c:otherwise>
					</c:choose>
			</script>
		</head>
		<body>
			<c:set value="${contactSummary}" var="contactSummary" scope="request"/>
			<jsp:include page="/WEB-INF/views/victim/includes/victimHeader.jsp"/>
			<h1>
				<a class="actionMenuItem" id="victimDocumentsActionMenuLink" href="${pageContext.request.contextPath}/victim/document/displayVictimDocumentsActionMenu.html?victim=${victimSummary.id}"></a>
				<fmt:message key="editVictimDocumentsTitle"/>
			</h1>
			<c:set value="${dockets}" var="dockets" scope="request"/>
			<jsp:include page="includes/editForm.jsp" />
		</body>
	</fmt:bundle>
</html>