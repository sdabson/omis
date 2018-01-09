
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.presentenceinvestigation.msgs.educationSectionSummary">
	<jsp:include page="listTable.jsp"/>
			
	<h1 style="margin-top:20px;">
		<fmt:message key="documentsListHeader"/>
	</h1>
		<jsp:include page="documentListTable.jsp"/>
</fmt:bundle>