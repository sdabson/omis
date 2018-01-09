<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="/WEB-INF/tld/omis.tld" prefix="omis" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle" />
<fmt:bundle basename="omis.offenderflag.msgs.offenderFlag">
	<ul>
		<sec:authorize access="hasRole('OFFENDER_FLAG_CATEGORY_CREATE') or hasRole('ADMIN')">
		<li>
			<a class="createLink" href="${pageContext.request.contextPath}/offenderFlagCategory/create.html"><span class="visibleLinkLabel"><fmt:message key="offenderFlagCategoryCreateTitle"/></span></a>
		</li>	
		</sec:authorize>	
	</ul>
</fmt:bundle>