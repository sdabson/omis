<%--
 - Author: Stephen Abson
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.msgs.common">
	<c:forEach var="address" items="${addresses}">
		<tr>
			<td>
				<a class="actionMenuItem" href="${pageContext.request.contextPath}/address/addressesActionMenu.html?address=${address.id}"></a>
			</td>
			<td>
				<c:out value="${address.value}"/>
			</td>
			<td>
				<c:out value="${address.zipCode.city.name}"/>,
				<c:out value="${address.zipCode.city.state.abbreviation}"/>
				<c:out value="${address.zipCode.value}"/>
				<c:if test="${not empty address.zipCode.extension}">-<c:out value="${address.zipCode.extension}"/></c:if>
			</td>
			<td><c:out value="${address.zipCode.city.state.country.name}"/></td>
		</tr>
	</c:forEach>
</fmt:bundle>