<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle" />
<fmt:bundle basename="omis.victim.msgs.victim">
	<ul>
		<c:if test="${not empty docket}">
		</c:if>
		<li>
			<a id="createVictimDocumentItemLink" class="createLink addLink" href="${pageContext.request.contextPath}/victim/document/edit.html?victim=${victim.id}&&docket=${docket.id}">
				<span class="visibleLinkLabel"><fmt:message key="addVictimDocumentLinkLabel"/></span></a>
		<li>
	</ul>
</fmt:bundle>