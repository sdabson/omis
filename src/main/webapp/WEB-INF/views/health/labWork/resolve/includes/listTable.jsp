<%--
 - Author: Yidong Li
 - Version: 0.1.0 (Nov 24, 2014)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:bundle basename="omis.health.msgs.health">
<form:form commandName="resolveLabWorkForm" class="editForm" id="resolveLabWorkForm" method="POST" action="${pageContext.request.contextPath}/health/labWork/resolve/save.html?offender=${offender.id}&facility=${facility.id}">
		<jsp:include page="listTableBodyContent.jsp"/>
</form:form>
</fmt:bundle>