<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.disciplinaryCode.msgs.disciplinaryCode">
	<ul>
		<sec:authorize access="hasRole('DISCIPLINARY_CODE_LIST') or hasRole('ADMIN')">
			<li>
				<a class="listLink" href="${pageContext.request.contextPath}/disciplinaryCode/list.html?supervisoryOrganization=${supervisoryOrganization.id}"><span class="visibleLinkLabel"><fmt:message key="listDisciplinaryCodesLink"/></span></a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>