<%--
 - Author: Stephen Abson
 - Version: 0.1.0 (Oct 30, 2013)
 - Since: OMIS 3.0
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="omis.config.msgs.config">
<c:forEach var="setting" items="${settings}">
	<tr>
		<td>
			<a class="viewEditLink" href="${pageContext.request.contextPath}/config/edit.html?setting=${setting.id}">
				<span class="linkLabel"><fmt:message key="viewEditLink"/></span>
			</a>
		</td>
		<td>
			<a class="removeLink" href="${pageContext.request.contextPath}/config/remove.html?setting=${setting.id}">
				<span class="linkLabel"><fmt:message key="removeLink"/></span>
			</a>
		</td>
		<td>
			<c:out value="${setting.name}"/>
		</td>
		<td>
			<c:out value="${setting.type.type.canonicalName}"/>
		</td>
		<td>
			<c:out value="${setting.value}"/>
		</td>
	</tr>
</c:forEach>
</fmt:bundle>