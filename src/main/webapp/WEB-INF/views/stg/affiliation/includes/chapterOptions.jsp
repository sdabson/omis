<%--
 - Author: Josh Divine
 - Version: 0.1.0 (Dec 07, 2016)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<option value="">...</option>
<c:forEach var="chapter" items="${chapters}">
<c:choose>
	<c:when test="${not empty defaultChapter and chapter eq defaultChapter}">
		<option value="${chapter.id}" selected="selected"><c:out value="${chapter.name}"/></option>
	</c:when>
	<c:otherwise>
		<option value="${chapter.id}"><c:out value="${chapter.name}"/></option>
	</c:otherwise>
</c:choose>
</c:forEach>