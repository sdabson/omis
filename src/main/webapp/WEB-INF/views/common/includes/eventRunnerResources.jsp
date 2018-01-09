<%-- Includes libraries for event runner if not loaded.
 - Author: Ryan Johns
 - Version: 0.1.0 (Oct 21, 2014)
 - Since: OMIS 3.0
 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<c:if test="${empty eventRunnerLIB}">
		<c:set var="eventRunnerLIB" value="true" scope="request"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/common/scripts/EventRunner.js?VERSION=1"></script>
	</c:if>