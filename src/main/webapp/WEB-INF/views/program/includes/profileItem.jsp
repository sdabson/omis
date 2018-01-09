<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Author: Josh Divine
 - Version: 0.1.0 (June 8, 2017)
 - Since: OMIS 3.0 
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.program.msgs.program">
	<div class="profileItem">
		<a href="${pageContext.request.contextPath}/program/list.html?offender=${offenderSummary.id}">
			<span>
				<c:choose>
		    		<c:when test="${programPlacementExists}">
		    			<fmt:message key="currentProgramPlacementsExistsLabel"/>
		    		</c:when>
		    		<c:otherwise>
		    			<fmt:message key="noCurrentProgramPlacementsExistsLabel"/>
		    	</c:otherwise>
	    	</c:choose>
			</span>
    	</a>
    </div>
</fmt:bundle>