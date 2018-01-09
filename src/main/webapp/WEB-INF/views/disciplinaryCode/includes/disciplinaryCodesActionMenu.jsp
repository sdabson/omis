<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.disciplinaryCode.msgs.disciplinaryCode">
	<ul>
		<sec:authorize access="hasRole('DISCIPLINARY_CODE_CREATE') or hasRole('ADMIN')">
			<li>
				<a class="createLink" href="${pageContext.request.contextPath}/disciplinaryCode/create.html?supervisoryOrganization=${supervisoryOrganization.id}"><span class="visibleLinkLabel"><fmt:message key="createDisciplinaryCodeLink"/></span></a>
			</li>
			<li>
				<a class="listLink" href="${pageContext.request.contextPath}/disciplinaryCode/supervisoryOrganizationSelection.html"><span class="visibleLinkLabel"><fmt:message key="listSupervisoryOrganizationsLink"/></span></a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>