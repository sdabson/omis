<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.offenderflag.msgs.offenderFlag">
<ul>
	<sec:authorize access="hasRole('OFFENDER_FLAG_CATEGORY_LIST') or hasRole('ADMIN')">
			<li>
				<a class="listLink" id="actionMenuLink" href="${pageContext.request.contextPath}/offenderFlagCategory/list.html">
					<fmt:message key="offenderFlagCategoryTitle"/>
				</a>
			</li>
	</sec:authorize>
</ul>
</fmt:bundle>