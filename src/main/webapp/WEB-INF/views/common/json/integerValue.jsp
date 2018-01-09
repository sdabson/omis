<%--
  - Displays a JSON integer value.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:choose>
	<c:when test="${integerValue ne null}">${integerValue}</c:when>
	<c:otherwise>${null}</c:otherwise>
</c:choose>