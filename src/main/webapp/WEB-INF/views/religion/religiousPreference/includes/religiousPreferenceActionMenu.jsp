<%-- Author: Stephen Abson --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.religion.msgs.religion">
	<ul>
	<sec:authorize access="hasRole('RELIGIOUS_PREFERENCE_LIST') or hasRole('ADMIN')">
		<li>
    		<a class="listLink" href="${pageContext.request.contextPath}/religion/religiousPreference/list.html?offender=${offender.id}">
    			<span class="visibleLinkLabel"><fmt:message key="listReligiousPreferencesLink"/></span></a>
    	</li>
    </sec:authorize>
	</ul>
</fmt:bundle>