<%--
 - Author: Yidong Li
 - Version: 0.1.0 (June 23, 2015)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<option value="">...</option>
<c:forEach var="zipCode" items="${zipCodes}">
	<option value="${zipCode.id}"><c:out value="${zipCode.value}"/></option>
</c:forEach>
