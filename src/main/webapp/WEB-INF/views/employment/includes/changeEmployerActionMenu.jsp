<!-- 
 - Author: Yidong Li
 - Version: 0.1.0 (Oct 31, 2016)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<fmt:bundle basename="omis.employment.msgs.employment">
	<ul>
		<sec:authorize access="hasRole('EMPLOYMENT_TERM_VIEW') or hasRole('ADMIN')">
			<li>
				<a class="viewEditLink" href="${pageContext.request.contextPath}/employment/edit.html?employmentTerm=${employmentTerm.id}"><span class="visibleLinkLabel"><fmt:message key="editEmploymentTermLabel"/></span>
				</a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>