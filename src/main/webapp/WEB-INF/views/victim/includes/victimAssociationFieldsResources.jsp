 <%-- 
 -
  - Author: Yidong Li
 - Version: 0.1.0 (May 6, 2016)
 - Since: OMIS 3.0
 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<jsp:include page="/WEB-INF/views/common/includes/formCSS.jsp"/>	
	<jsp:include page="/WEB-INF/views/common/includes/jQueryResources.jsp"/>	
	<jsp:include page="/WEB-INF/views/common/includes/serverConfigResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/messageResolverResources.jsp"/>
	<c:if test="${empty victimAssociationFieldsLIB}">
		<c:set var="victimAssociationFieldsLIB" value="true" scope="request"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/victim/scripts/JQuery/jquery.omis.victimAssociationFields.js"> </script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/victim/scripts/victimAssociationFields.js"> </script>
	</c:if>