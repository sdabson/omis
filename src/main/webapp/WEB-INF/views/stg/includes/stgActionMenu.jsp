<%-- Author: Stephen Abson --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.stg.msgs.stg">
<ul>
	<sec:authorize access="hasRole('STG_AFFILIATION_LIST') or hasRole('ADMIN')">
		<li><a class="listLink" href="${pageContext.request.contextPath}/stg/list.html?offender=${offender.id}">
			<span class="visibleLinkLabel">
				<fmt:message key="listStgAffiliationsLink"/>
			</span>
			</a>
		</li>
	</sec:authorize>
</ul>
</fmt:bundle>