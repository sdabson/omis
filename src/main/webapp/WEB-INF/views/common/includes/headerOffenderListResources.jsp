<%-- Includes offender list resources if not loaded.
 - Author: Ryan Johns, Joel Norris
 - Version: 0.1.1 (Apr 6, 2017)
 - Since: OMIS 3.0
 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<jsp:include page="/WEB-INF/views/common/includes/headerListResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/toolsResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/headerOffenderHeaderResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/messageResolverResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/interactiveImageResources.jsp"/>
	<jsp:include page="/WEB-INF/views/common/includes/interactiveTableResources.jsp"/>