<?xml version="1.0" encoding="UTF-8"?>
<%-- Author: Ryan Johns
 - Version: 0.1.0 (Mar 21, 2016)
 - Since: OMIS 3.0 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.visitation.msgs.visitation">
	<div class="profileItem">
		<a href="${pageContext.request.contextPath}/visitation/list.html?offender=${offenderSummary.id}">
    		<span>
    			<fmt:message key="visitationCountLabel">
    				<fmt:param value="${visitorCount}"/>
    				<fmt:param value="${visitCount}"/>
    			</fmt:message>
    		</span>
    	</a>
    </div>
</fmt:bundle>