<%--
 - List of items summarizing location terms.
 -
 - Author: Stephen Abson
 - Author: Sheronda Vaughn
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${not empty locationTermSummaries}">
	<jsp:include page="associatedLocationTerms.jsp"/>
</c:if>