<?xml version="1.0" encoding="UTF-8"?>
<%-- Author: Ryan Johns
 - Version: 0.1.0 (Mar 16, 2016)
 - Since: OMIS 3.0 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.military.msgs.military">
	<div class="profileItem">
		<a href="${pageContext.request.contextPath}/military/list.html?offender=${offenderSummary.id}">
			<span>
				<c:choose>
					<c:when test="${militaryServiceTermExists}">
						<fmt:message key="serviceTermExistsLabel"/>
					</c:when>
					<c:otherwise>
						<fmt:message key="noServiceTermExistsLabel"/>
					</c:otherwise>
				</c:choose>
			</span>
    	</a>
    	<div class="moduleInfo">
    		<span>
    			
			</span>
		</div>
	</div>
</fmt:bundle>