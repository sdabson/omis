<?xml version="1.0" encoding="UTF-8"?>
<%-- Author: Ryan Johns
 - Version: 0.1.0 (Mar 17, 2016)
 - Since: OMIS 3.0 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.separationneed.msgs.separationNeed">
	<div class="profileItem">
		<a href="${pageContext.request.contextPath}/separationNeed/list.html?offender=${offenderSummary.id}">
			<span>
				<c:choose>
					<c:when test="${not empty separationNeedProfileItemSummary && not empty separationNeedProfileItemSummary.activeCount}">
						<c:set var="activeCount" value="${separationNeedProfileItemSummary.activeCount}"/>
					</c:when>
					<c:otherwise>
						<c:set var="activeCount" value="0"/>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${not empty separationNeedProfileItemSummary && not empty separationNeedProfileItemSummary.totalCount}">
						<c:set var="inactiveCount" value="${separationNeedProfileItemSummary.totalCount}"/>
					</c:when>
					<c:otherwise>
						<c:set var="inactiveCount" value="0"/>
					</c:otherwise>
				</c:choose>
    			<fmt:message key="separationNeedCountLabel">
    				<fmt:param value="${activeCount}"/>
    				<fmt:param value="${inactiveCount}"/>
    			</fmt:message>
    		</span>
    	</a>
    </div>
</fmt:bundle>