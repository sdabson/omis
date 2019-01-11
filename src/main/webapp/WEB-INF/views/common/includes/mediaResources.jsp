<%--
 - Include media resources if not loaded.
 - Author: Joel Norris
 - Version: 0.1.0 (Novemeber 2, 2018)
 - Since: OMIS 3.0
 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/views/common/includes/serverConfigResources.jsp"/>
<c:if test="${empty mediaResources}">
	<c:set var="mediaResources" value="true" scope="request"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/scripts/media.js?VERSION=7"></script>
</c:if>