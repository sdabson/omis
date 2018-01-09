<?xml version="1.0" encoding="UTF-8"?>
<%--
 - Author: Ryan Johns
 - Version: 0.1.0 (Mar 31, 2014)
 - Since: OMIS 3.0
 --%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 <fmt:bundle basename="omis.health.msgs.health">
<ul>
	<c:forEach var="facility" items="${facilities}">
		<li>
			<a href="${pageContext.request.contextPath}/health/referral/referralCenter.html?facility=${facility.id}&filterByReferralType=${referralType.name}&amp;filterByStartDate=<fmt:formatDate value='${filterByStartDate}' pattern='MM/dd/yyyy'/>">
				<fmt:message key="title">
					<fmt:param value="${facility.name}"/>
				</fmt:message>
			</a>
		</li>
	</c:forEach>
</ul>
</fmt:bundle>