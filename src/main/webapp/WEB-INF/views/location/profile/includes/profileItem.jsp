<?xml version="1.0" encoding="UTF-8"?>
<%-- Author: Ryan Johns
 - Version: 0.1.0 (Mar 16, 2016)
 - Since: OMIS 3.0 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.location.msgs.location">
	<div class="profileItem">
		<a href="${pageContext.request.contextPath}/locationTerm/profile.html?offender=${offenderSummary.id}">
			<span>
    			<c:choose>
    				<c:when test="${locationTermExists}">
    						<fmt:message key="activeLocationTermLabel"/>
    				</c:when>
    				<c:otherwise>
    						<fmt:message key="nonActiveTermLabel"/>
    				</c:otherwise>
    			</c:choose>
    		</span>
    	</a>
    </div>
</fmt:bundle>