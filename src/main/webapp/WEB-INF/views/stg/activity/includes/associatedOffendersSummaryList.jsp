<%--
 - List of items summarizing location terms.
 -
 - Author: Trevor Isles
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${not empty involvementSummaries}">
	<jsp:include page="associatedInvolvedOffenders.jsp"/>
</c:if>