<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.warrant.msgs.warrant">
	<ul>
		<sec:authorize access="hasRole('WARRANT_CREATE') or hasRole('WARRANT_EDIT') or hasRole('ADMIN')">
			<li>
				<a id="createWarrantNoteItemLink" class="createLink" href="${pageContext.request.contextPath}/warrant/createWarrantNoteItem.html?warrantNoteItemIndex=${warrantNoteItemIndex}"><span class="visibleLinkLabel"><fmt:message key="addWarrantNoteLink"/></span></a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>