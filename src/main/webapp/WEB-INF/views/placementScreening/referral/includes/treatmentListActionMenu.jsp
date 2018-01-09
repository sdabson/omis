<?xml version="1.0" encoding="UTF-8"?>
<%-- 
	- Author: Ryan Johns
	- Version: 0.1.0 (Mar 13, 2015)
	- Since: OMIS 3.0 
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.placementscreening.msgs.form">
<ul>
	<c:forEach items="${categories}" var="category">
	<li>
		<a class="createLink addTreatment" href="${pageContext.request.contextPath}/placementScreening/referral/addTreatmentScreening.html?category=<c:out value="${category}"/>">
			<fmt:message key="ProgramCategoryLabel.${category}.addLabel"></fmt:message>
		</a>
	</li>
	</c:forEach>
</ul>
</fmt:bundle>