 <%-- 
 - Includes JS libraries person fields functionality.
 -
 - Usage: See personFields.js
 - Author: Joel Norris
 - Version: 0.1.0 (June 18, 2015)
 - Since: OMIS 3.0
 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<jsp:include page="/WEB-INF/views/common/includes/formCSS.jsp"/>	
	<jsp:include page="/WEB-INF/views/common/includes/jQueryResources.jsp"/>	
	<jsp:include page="/WEB-INF/views/common/includes/serverConfigResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/messageResolverResources.jsp"/>
	<c:if test="${empty personFieldsLIB}">
		<c:set var="personFieldsLIB" value="true" scope="request"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/person/scripts/JQuery/jquery.omis.personFields.js?VERSION=2"> </script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/person/scripts/personFields.js?VERSION=2"> </script>
	</c:if>