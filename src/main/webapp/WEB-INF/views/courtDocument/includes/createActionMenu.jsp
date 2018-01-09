<?xml version="1.0" encoding="UTF-8"?>
<%-- Author: Ryan Johns
 - Version: 0.1.0 (Dec 7, 2015)
 - Since: OMIS 3.0 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.courtdocument.msgs.document">
<ul>
	<li>
		<a href="${pageContext.request.contextPath}/courtCase/document/list.html?offender=${offender.id}">
			<fmt:message key="courtCaseDocumentsLabel"/>
		</a>
	</li>
</ul>
</fmt:bundle>