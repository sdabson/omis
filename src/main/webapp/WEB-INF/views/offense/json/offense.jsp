<%--
  - Offense.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="newLine" value="<%= '\n' %>" />
<c:set var="tabCharacter" value="<%= '\t' %>" />
<c:set var="doubleQuote" value="<%= '\"' %>"/>
<c:set var="doubleQuoteString" value="\\\""/>
{
	"id": ${offense.id},
	"name": "<c:out value='${fn:replace(fn:replace(fn:replace(offense.name, newLine, ""), tabCharacter, " "), doubleQuote, doubleQuoteString)}' escapeXml='false'/>",
	"url": '${offense.url}'
}