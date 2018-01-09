<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<fmt:bundle basename="omis.offense.msgs.offense">
	<c:forEach var="offense" items="${offenses}" varStatus="status">
		<tr>
			<td>
				<a class="viewEditLink" href="${pageContext.request.contextPath}/offense/edit.html?offense=${offense.id}">
					<span class="linkLabel"><fmt:message key="viewEditLabel"/></span>
				</a>
				<a class="removeLink" href="${pageContext.request.contextPath}/offense/remove.html?offense=${offense.id}">
					<span class="linkLabel"><fmt:message key="removeLabel"/></span>
				</a>
			</td>
			<td>
				<c:out value="${offense.violationCode}"/>
			</td>
			<td>
				<c:out value="${offense.name}"/>
			</td>
		</tr>
	</c:forEach> 
</fmt:bundle>