<?xml version="1.0" encoding="UTF-8"?>
<%-- Author: Trevor Isles
 - Version: 0.1.0 (August 3, 2017)
 - Since: OMIS 3.0 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.courtcasecondition.msgs.courtCaseConditions">
	<div class="profileItem">
		<a href="${pageContext.request.contextPath}/courtCaseCondition/list.html?offender=${offenderSummary.id}">
			<span>
    			<fmt:message key="courtCaseConditionCountLabel">
    				<fmt:param value="${courtCaseConditionCount}"/>
    			</fmt:message>
    		</span>
    	</a>
    </div>
</fmt:bundle>