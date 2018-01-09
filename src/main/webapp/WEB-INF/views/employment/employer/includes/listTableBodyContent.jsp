<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="address" uri="/WEB-INF/tld/address.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.employment.msgs.employment" var="employerBundle"/>
<c:forEach var="employerSummary" items="${searchSummaries}">
<c:if test="${not empty employerSummary.employerLocationOrganizationName}">
<tr>
	<td></td>
	<td><c:out value="${employerSummary.employerLocationOrganizationName}"></c:out></td>
	<td><c:if test="${employerSummary.address == true}">
			<address:formatSummary value="${employerSummary}" format="LINE1"/> <address:formatSummary value="${employerSummary}" format="LINE2"/>
		</c:if>
	</td>
	<td><c:out value="${employerSummary.addressCityName}"></c:out></td>
	<td><c:out value="${businessPhone}"></c:out></td>	
	<td><c:out value="${employerSummary.offenderEmploymentCount}"></c:out></td>
</tr>
</c:if>
</c:forEach>