<?xml version="1.0" encoding="UTF-8"?>
<%-- Author: Ryan Johns
 - Version: 0.1.0 (Nov 27, 2015)
 - Since: OMIS 3.0 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<fmt:bundle basename="omis.document.msgs.document">
	<head>
		<jsp:include page="/WEB-INF/views/common/includes/headerListResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/headerGeneralResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/headerOffenderHeaderResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/tabsResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/eventRunnerResources.jsp"/>
		<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
		<script type="text/javascript">
			var documentAssociationType = "${documentAssociationType}";
		</script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/document/scripts/profile.js?VERSION=3"></script>
		<title>
			<fmt:message key="profileHeaderLabel">
				<fmt:param value="${offenderSummary.lastName}"/>
				<fmt:param value="${offenderSummary.firstName}"/>
				<fmt:param value="${offenderSummary.offenderNumber}&#8203;"/>
			</fmt:message> 
		</title>
	</head>
	<body>
	<jsp:include page="/WEB-INF/views/offender/includes/offenderHeader.jsp"/>
		<ul class="tabs">
			<li class="tab_li">
				<span class="tab">
					<a href="list.html?offender=${offenderSummary.id}" id="all" class="swap documentLink">
						<fmt:message key="allLabel"/>
					</a>
				</span>
			</li>
		<c:forEach var="documentItem" items="${documentItems}" varStatus="status">
			<li class="tab_li">
				<span class="tab">
					<c:url value="${documentItem.listView}" var="url">
						<c:param name="offender" value="${offenderSummary.id}"/>
					</c:url>
					<a href="${url}" id="${documentItem.documentAssociationNameKey}" class="swap documentLink">
						<fmt:message key="${documentItem.listLinkLabelKey}"/>
					</a>
				</span>
			</li>
		</c:forEach>
		</ul>
		
		<div id="documents">
			<jsp:include page="includes/listTable.jsp"/>
		</div>
	</body>
</fmt:bundle>
</html>