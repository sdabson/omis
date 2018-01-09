<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.disciplinaryCode.msgs.disciplinaryCode">
	<ul>
		<sec:authorize access="hasRole('DISCIPLINARY_CODE_VIEW') or hasRole('ADMIN')">
			<li>
				<a class="viewEditLink" href="${pageContext.request.contextPath}/disciplinaryCode/edit.html?supervisoryOrganizationCode=${supervisoryOrganizationCode.id}" ><span class="visibleLinkLabel"><fmt:message key="editDisciplinaryCodeLink"/></span></a>
			</li>
		</sec:authorize>
		<sec:authorize access="hasRole('DISCIPLINARY_CODE_REMOVE') or hasRole('ADMIN')">
			<li>
				<a class="removeLink" href="${pageContext.request.contextPath}/disciplinaryCode/remove.html?supervisoryOrganizationCode=${supervisoryOrganizationCode.id}"><span class="visibleLinkLabel"><fmt:message key="removeDisciplinaryCodeLink"/></span></a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>