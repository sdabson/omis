<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Author: Ryan Johns
 - Version: 0.1.0 (Apr 10, 2014)
 - Since: OMIS 3.0
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.health.msgs.health">
	<ul>
		<li>
			<a href="${pageContext.request.contextPath}/health/provider/viewSchedule.html?provider=${provider.id}" id="providerMenu">
				<fmt:message key="viewSchedule"/>
			</a></li>
	</ul>
</fmt:bundle>