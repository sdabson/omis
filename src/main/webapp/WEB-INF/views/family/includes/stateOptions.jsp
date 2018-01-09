<%--
 - Author: Yidong Li
 - Version: 0.1.0 (June 23, 2015)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.region.msgs.state">
<option value="">...</option>
<c:forEach var="state" items="${states}">
	<option value="${state.id}"><c:out value="${state.name}"/></option>
</c:forEach>
</fmt:bundle>