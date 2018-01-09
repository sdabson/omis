<%--
 - Author: Yidong Li
 - Version: 0.1.0 (June 22, 2015)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<option value="">...</option>
<c:forEach var="city" items="${cities}">
	<option value="${city.id}"><c:out value="${city.name}"/></option>
</c:forEach>