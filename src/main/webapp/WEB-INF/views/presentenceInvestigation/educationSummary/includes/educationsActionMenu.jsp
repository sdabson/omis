<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.education.msgs.education">
	<ul>
		<sec:authorize access="hasRole('EDUCATION_VIEW') or hasRole('ADMIN')">
			<li>
				<a class="listLink" href="${pageContext.request.contextPath}/education/list.html?offender=${offender.id}"><span class="visibleLinkLabel"><fmt:message key="listEducationsLink"/></span></a>
			</li>
			<li>
				<a id="refreshEducationsLink" class="refreshLink" ><span class="visibleLinkLabel"><fmt:message key="refreshEducationsLink"/></span></a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>