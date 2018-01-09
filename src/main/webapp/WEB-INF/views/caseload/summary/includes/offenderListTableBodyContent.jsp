<%--
 - Author: Ryan Johns
 - Version: 0.1.0 (Jan 02, 2014)
 - Since: OMIS 3.0
 --%>
<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.caseload.msgs.caseload">
<c:forEach var="offender" items="${offenders}">
	<tr>
		<td><a href="${pageContext.request.contextPath}/offender/showProfile.html?offenderNumber=${offender.offenderNumber}">
		load profile
		</a>
		<a href="${pageContext.request.contextPath}/offender/modules.html?offender=${offender.id}">
		load modules
		</a>
		</td>
		<td>
		<img src="${pageContext.request.contextPath/offenderPhoto/displayProfilePhoto.html?offender=${offender.id}"/>
		</td>
		<td>
			<c:out value="${offender.offenderLastName}, ${offender.offenderFirstName}"/>
		</td>
		<td>
			<fmt:formatDate value="${offender.dateRange.startDate}"/>, <fmt:formatDate value="${offender.dateRange.endDate}"/>
		</td>
	</tr>
</c:forEach>
</fmt:bundle>