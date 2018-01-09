<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<fmt:bundle basename="omis.search.msgs.search">

<c:forEach var="offender" items="${offenders}" varStatus="status">
	<tr>
		<td><a class="viewEditLink" href="${pageContext.request.contextPath}/personSearch/offender.json?offender=${offender.personId}">
			<fmt:message key="actionLabel"/>
			</a>
		</td>
		<td><c:out value="${offender.lastName}, ${offender.firstName} ${offender.middleName} (${offender.offenderNumber})"/></td>
	</tr>
</c:forEach>
</fmt:bundle>