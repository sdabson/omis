<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<fmt:bundle basename="omis.search.msgs.search">

<c:forEach var="caseLoad" items="${caseLoadListing}" varStatus="status">
	<tr>
		<td><a class="viewEditLink" href="${pageContext.request.contextPath}/caseLoadSearch/caseLoad.json?caseLoad=${caseLoad.id}">
			<fmt:message key="actionLabel"/>
			</a>
		</td>
		<td><c:out value="${caseLoad.title}"/></td>
		<td><c:out value="${caseLoad.description}"/></td>
	</tr>
</c:forEach>
</fmt:bundle>