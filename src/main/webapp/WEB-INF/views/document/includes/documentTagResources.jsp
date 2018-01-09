<?xml version="1.0" encoding="UTF-8"?>
<%-- Author: Ryan Johns
 - Version: 0.1.0 (Dec 22, 2015)
 - Since: OMIS 3.0 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
<c:if test="${empty documentTagResources}">
<c:set var="documentTagResources" value="true" scope="request"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/document/scripts/documentTag.js?VERSION=1"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/document/style/documentTag.css?VERSION=1"/>
</c:if>