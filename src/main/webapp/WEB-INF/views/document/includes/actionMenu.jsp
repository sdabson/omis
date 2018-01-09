<?xml version="1.0" encoding="UTF-8"?>
<%-- Author: Ryan Johns
 - Version: 0.1.0 (Dec 3, 2015)
 - Since: OMIS 3.0 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.document.msgs.document">
<ul>
	<c:forEach items="${documentItems}" var="documentItem" varStatus="status">
		<li>
			<a href="${pageContext.request.contextPath}/${documentItem.createView}?offender=${offender.id}&filter=all" class="createLink">
				<fmt:message key="${documentItem.createLinkLabelKey}"/>
			</a>
		</li>
	</c:forEach> 
</ul>
</fmt:bundle>