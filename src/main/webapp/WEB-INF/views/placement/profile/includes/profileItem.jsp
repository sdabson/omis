<?xml version="1.0" encoding="UTF-8"?>
<%-- Author: Ryan Johns
 - Version: 0.1.0 (Mar 16, 2016)
 - Since: OMIS 3.0 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.placement.msgs.placement">
	<div class="profileItem">
		<a href="${pageContext.request.contextPath}/placement/home.html?offender=${offenderSummary.id}">
			<span>
				<c:choose>
		    		<c:when test="${placementExists}">
		    			<fmt:message key="currentPlacementExistsLabel"/>
		    		</c:when>
		    		<c:otherwise>
		    			<fmt:message key="noCurrentPlacementExistsLabel"/>
		    	</c:otherwise>
	    	</c:choose>
			</span>
    	</a>
    </div>
</fmt:bundle>