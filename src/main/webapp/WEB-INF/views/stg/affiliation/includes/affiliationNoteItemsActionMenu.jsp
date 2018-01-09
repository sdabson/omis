<!-- 
 - Author: Trevor Isles
 - Version: 0.1.0 (Nov 17, 2016)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.stg.msgs.stg">
	<ul>
		<sec:authorize access="hasRole('STG_AFFILIATION_VIEW') or hasRole('ADMIN')">
			<li>
				<a id="createAffiliationNoteItemLink" class="createLink" href="${pageContext.request.contextPath}/stg/affiliation/createAffiliationNoteItem.html?affiliationNoteItemIndex=${affiliationNoteItemIndex}"><span class="visibleLinkLabel"><fmt:message key="createAffiliationNoteLabel"/></span></a>
			</li>
		</sec:authorize>
		<li></li>
		<li></li>
	</ul>
</fmt:bundle>