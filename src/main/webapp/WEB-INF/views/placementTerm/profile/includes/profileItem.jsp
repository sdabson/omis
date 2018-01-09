<?xml version="1.0" encoding="UTF-8"?>
<%-- Author: Ryan Johns
 - Author: Stephen Abson
 - Version: 0.1.0 (Mar 17, 2016)
 - Since: OMIS 3.0 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.supervision.msgs.placementTerm">
	<div class="profileItem">
	<a href="${pageContext.request.contextPath}/supervision/profile.html?offender=${offenderSummary.id}">
		<span>
    		<c:choose>
    			<c:when test="${placementTermExists}">
    					<fmt:message key="currentPlacementTermExistsLabel"/>
    			</c:when>
    			<c:otherwise>
    					<fmt:message key="noCurrentPlacementTermExistsLabel"/>
    			</c:otherwise>
    		</c:choose>
    	</span>
    </a>
    </div>
</fmt:bundle>