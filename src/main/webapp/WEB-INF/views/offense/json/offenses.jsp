<%--
 - Offenses.
 -
 - Author: Josh Divine
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="newLine" value="<%= '\n' %>" />
<c:set var="tabCharacter" value="<%= '\t' %>" />
<c:set var="doubleQuote" value="<%= '\"' %>"/>
<c:set var="doubleQuoteString" value="\\\""/>
[
<c:forEach var="offense" items="${offenses}" varStatus="status">
	{
		"label": "<c:out value='${offense.violationCode} - ${fn:replace(fn:replace(fn:replace(offense.name, newLine, ""), tabCharacter, " "), doubleQuote, doubleQuoteString)}' escapeXml='false'/>",
		"value": "${offense.id}"
	}
	<c:if test="${not status.last}">,</c:if>
</c:forEach>
]