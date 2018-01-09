<?xml version="1.0" encoding="UTF-8"?>
<%-- Author: Ryan Johns
 - Version: 0.1.0 (Mar 11, 2016)
 - Since: OMIS 3.0 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.criminalassociation.msgs.criminalAssociation">
	<div class="profileItem">
		<a href="${pageContext.request.contextPath}/criminalAssociation/list.html?offender=${offenderSummary.id}">
			<span>
   				<fmt:message key="criminalAssociatesCountLabel">
   					<fmt:param value="${criminalAssociatesCount}"/>
   				</fmt:message>
   			</span>
   		</a>
   	</div>
</fmt:bundle>