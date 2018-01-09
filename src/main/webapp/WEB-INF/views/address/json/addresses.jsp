<%--
  - Returns a JSON array of addresses.
  -
  - Author: Yidong Li (original)
  - Author: Stephen Abson
  - Author: Josh Divine
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="newLine" value="<%= '\n' %>" />
<c:set var="carriageReturn" value="<%= '\r' %>"/>
<c:set var="tabCharacter" value="<%= '\t' %>" />
<c:set var="doubleQuote" value="<%= '\"' %>"/>
<c:set var="doubleQuoteString" value="\\\""/>
<c:set var="backslash" value="\\"/>
<c:set var="doubleBackslash" value="\\\\"/>
[
<c:forEach var="address" items="${addresses}" varStatus="status">
	{
		"label": "<c:out value='${fn:replace(fn:replace(fn:replace(fn:replace(fn:replace(address.value, newLine, " "), carriageReturn, ""), tabCharacter, " "), backslash, doubleBackslash), doubleQuote, doubleQuoteString)}' escapeXml='false'/>, <c:out value='${address.zipCode.city.name}'/>, <c:out value='${address.zipCode.city.state.abbreviation}'/> <c:out value='${address.zipCode.value}'/>",
		"value": "${address.id}"
	}
	<c:if test="${not status.last}">,</c:if>
</c:forEach>
]