<!-- 
 - Author: Trevor Isles
 - Version: 0.1.0 (April 20, 2017)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.prisonterm.msgs.prisonTerm">
	<ul>
		<sec:authorize access="hasRole('PRISON_TERM_LIST') or hasRole('ADMIN')">
			<li>
				<a class="listLink" href="${pageContext.request.contextPath}/prisonTerm/list.html?offender=${offender.id}">
				<span class="visibleLinkLabel"><fmt:message key="listPrisonTermsLink"/></span></a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>