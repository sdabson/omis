<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.person.msgs.person">
<ul>
	<li><sec:authorize access="hasRole('OFFENDER_VIEW') or hasRole('OFFENDER_EDIT') or hasRole('ADMIN')">
		<a class="personalDetailsLink" href="${pageContext.request.contextPath}/offender/personalDetails/edit.html?offender=${offender.id}">
			<fmt:message key="editPersonHeader"/></a></sec:authorize></li>
</ul>
</fmt:bundle>