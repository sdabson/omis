<%-- Frame messaging resources if not loaded.
 - Author: Ryan Johns
 - Version 0.1.0 (Jun 19, 2015)
 - Since: OMIS 3.0 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${empty messageEventResources}">
	<c:set var="messageEventResources" scope="request"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/scripts/MessageEvent.js?VERSION=1"></script>
</c:if>