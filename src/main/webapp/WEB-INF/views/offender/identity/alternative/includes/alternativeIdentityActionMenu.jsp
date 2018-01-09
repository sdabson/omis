<%-- Author: Stephen Abson --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.offender.msgs.alternativeOffenderIdentity">
<ul>
	<li><sec:authorize access="hasRole('OFFENDER_ALT_IDENTITY_LIST') or hasRole('ADMIN')">
			<a class="listLink" href="${pageContext.request.contextPath}/offender/identity/alternative/list.html?offender=${offender.id}">
				<fmt:message key="listAlternativeOffenderIdentitiesTitle"/></a></sec:authorize></li>
</ul>
</fmt:bundle>