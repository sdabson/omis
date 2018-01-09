<%--
 - Author: Stephen Abson
 - Version: 0.1.0 (Oct 30, 2013)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<option value="">...</option>
<c:forEach var="timeBlock" items="${timeBlocks}">
	<option value="${timeBlock.id}"><c:out value="${timeBlock.name}"/></option>
</c:forEach>