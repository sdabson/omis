<?xml version="1.0" encoding="UTF-8"?>
<%-- Author: Ryan Johns
 - Version: 0.1.0 (Mar 16, 2016)
 - Since: OMIS 3.0 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.need.msgs.need">
	<div class="profileItem">
		<a href="${pageContext.request.contextPath}/need/casePlanObjective/list.html?offender=${offenderSummary.id}">
			<span>
    			<fmt:message key="needsCountLabel">
    				<fmt:param value="${needsCount}"/>
    			</fmt:message>
    		</span>
    	</a>
    </div>
</fmt:bundle>