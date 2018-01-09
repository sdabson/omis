<%--
 - Includes libraries for session config if not loaded.
 -
 - Author: Stephen Abson
 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<c:if test="${empty sessionConfigLIB}">
		<c:set var="sessionConfigLIB" value="true" scope="request"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/scripts/SessionConfig.js?VERSION=1"></script>
	</c:if>