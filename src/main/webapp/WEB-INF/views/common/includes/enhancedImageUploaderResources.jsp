<%--
 - Include media resources if not loaded.
 - Author: Joel Norris
 - Version: 0.1.0 (Novemeber 2, 2018)
 - Since: OMIS 3.0
 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/views/common/includes/serverConfigResources.jsp"/>
<jsp:include page="/WEB-INF/views/common/includes/modalResources.jsp"/>
<c:if test="${empty enhancedImageUploaderResources}">
	<c:set var="enhancedImageUploaderResources" value="true" scope="request"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/common/style/enhancedImageUploader.css?VERSION=1.2"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/scripts/enhancedImageUploader.js?VERSION=1.2"> </script>
</c:if>