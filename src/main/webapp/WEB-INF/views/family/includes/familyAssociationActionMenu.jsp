<!-- 
 - Author: Yidong Li
 - Version: 0.1.0 (June 12, 2015)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.family.msgs.family">
	<ul>
		<sec:authorize access="hasRole('FAMILY_ASSOCIATION_LIST') or hasRole('ADMIN')">
			<li>
				<a class="listLink" href="${pageContext.request.contextPath}/family/list.html?offender=${offender.id}"><span class="visibleLinkLabel"><fmt:message key="listFamilyAssociationsLink"/></span>
				</a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>