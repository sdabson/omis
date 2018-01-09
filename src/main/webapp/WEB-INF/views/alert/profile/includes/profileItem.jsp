<?xml version="1.0" encoding="UTF-8"?>
<%-- Author: Ryan Johns
 - Version: 0.1.0 (Mar 10, 2016)
 - Since: OMIS 3.0 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.alert.msgs.alert">
	<c:choose>
		<c:when test="${alertCount gt 0}">
			<c:set var="profileItemClass" value="profileItem active"/>
		</c:when>
		<c:otherwise>
			<c:set var="profileItemClass" value="profileItem"/>
		</c:otherwise>
	</c:choose>
	<div class="${profileItemClass}">
		<a href="${pageContext.request.contextPath}/alert/list.html?offender=${offenderSummary.id}">
			<span>
   				<fmt:message key="alertCountLabel">
   					<fmt:param value="${activeAlertCount}"/>
   					<fmt:param value="${inactiveAlertCount}"/>
   				</fmt:message>
   			</span>
   		</a>
   	</div>
</fmt:bundle>