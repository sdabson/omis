<?xml version="1.0" encoding="UTF-8"?>
<%-- Author: Ryan Johns
 - Version: 0.1.0 (Mar 18, 2016)
 - Since: OMIS 3.0 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.vehicle.msgs.vehicle">
	<div class="profileItem">
		<a href="${pageContext.request.contextPath}/vehicle/list.html?offender=${offenderSummary.id}">
			<span>
    			<fmt:message key="vehicleAssociationCountLabel">
    				<fmt:param value="${vehicleAssociationCount}"/>
    			</fmt:message>
    		</span>
    	</a>
    </div>
</fmt:bundle>