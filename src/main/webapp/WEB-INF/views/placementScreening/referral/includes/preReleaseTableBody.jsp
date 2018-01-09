<?xml version="1.0" encoding="UTF-8"?>
<%-- Author: Ryan Johns
 - Version: 0.1.0 (Feb 10, 2015)
 - Since: OMIS 3.0 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.placementscreening.msgs.form">
	<thead>
		<tr>
			<th></th>
			<th><fmt:message key="orderLabel"/></th>
			<th><fmt:message key="preReleaseScreeningFacilityLabel"/></th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${screeningItems}" var="preReleaseScreeningItem" varStatus="status">
		<c:set var="index" value="${status.index}" scope="request"/>
		<c:set var="preReleaseScreeningItem" value="${preReleaseScreeningItem}" scope="request"/>
		<jsp:include page="preReleaseTableBodyContent.jsp"/>
	</c:forEach>
	</tbody>
</fmt:bundle>