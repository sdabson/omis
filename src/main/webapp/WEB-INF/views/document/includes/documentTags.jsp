<?xml version="1.0" encoding="UTF-8"?>
<%-- Author: Ryan Johns
 - Version: 0.1.0 (Dec 17, 2015)
 - Since: OMIS 3.0 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<fmt:bundle basename="omis.document.msgs.document">
<label class="tagListLabel">
	<a href="${pageContext.request.contextPath}/documents/addDocumentTag.html" class="createTagLink createLink">
		<fmt:message key="tagNameLabel"/>
	</a>
</label>
<ul class="documentTagBody">
<c:forEach items="${form.documentTagItems}" var="documentTagItem" varStatus="status">
	<c:set value="${status.index}" var="index" scope="request"/>
	<c:set value="${documentTagItem}" var="documentTagItem" scope="request"/>
	<jsp:include page="documentTagListItem.jsp"/>
</c:forEach>
</ul>
</fmt:bundle>