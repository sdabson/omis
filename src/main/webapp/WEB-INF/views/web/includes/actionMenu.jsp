<%-- Author: Stephen Abson --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<ul>
	<c:forEach var="actionMenuItem" items="${actionMenu.items}">
		<sec:authorize access="${actionMenuItem.access}">
		<fmt:setBundle basename="${actionMenuItem.messageBundle}" var="messageBundle"/>
			<li><a href="${pageContext.request.contextPath}${actionMenuItem.url}" class="${actionMenuItem.className}" id="${actionMenuItem.id}" title="<fmt:message key='${actionMenuItem.messageKey}' bundle='${messageBundle}'/>"><fmt:message key="${actionMenuItem.messageKey}" bundle="${messageBundle}"/></a></li>
		</sec:authorize>
	</c:forEach>
</ul>