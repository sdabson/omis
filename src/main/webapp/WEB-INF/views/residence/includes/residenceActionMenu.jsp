<!-- 
 - Author: Sheronda Vaughn
 - Version: 0.1.0 (Mar 23, 2015)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.residence.msgs.residence">
	<ul>
		<sec:authorize access="hasRole('RESIDENCE_TERM_LIST') or hasRole('NON_RESIDENCE_TERM_LIST') or hasRole('ADMIN')">
		<li>
			<a class="listLink" href="${pageContext.request.contextPath}/residence/list.html?offender=${offender.id}">
				<fmt:message key="residencesTitle"/>
			</a>
		</li>
		</sec:authorize>
	</ul>
</fmt:bundle>