<%--
  - Displays a JSON boolean value.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:choose>
	<c:when test="${booleanValue eq true}">{true}</c:when>
	<c:when test="${booleanValue eq false}">{false}</c:when>
	<c:otherwise>{null}</c:otherwise>
</c:choose>