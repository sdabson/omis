<%-- Includes list resources if not loaded.
 - Author: Ryan Johns
 - Version: 0.1.0 (Oct 21, 2014)
 - Since: OMIS 3.0
 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<jsp:include page="/WEB-INF/views/common/includes/jQueryResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/serverConfigResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/listCSS.jsp"/>
	<c:if test="${empty listResources}">
		<c:set var="listResources" value="true" scope="request"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/scripts/list.js?VERSION=1"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/scripts/JQuery/jquery.omis.list.js"></script>
	</c:if>