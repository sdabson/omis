<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.offenderflag.msgs.offenderFlag">
	<ul>
		<sec:authorize access="hasRole('ADMIN') or hasRole('OFFENDER_FLAG_CATEGORY_VIEW')">
			<li>
				<a class="viewEditLink" href="${pageContext.request.contextPath}/offenderFlagCategory/edit.html?offenderFlagCategory=${offenderFlagCategory.id}">
					<span class="linkLabel"><fmt:message key="viewEditLink" bundle="${commonBundle}"/></span>
				</a>
			</li>
			<li>
				<a class="removeLink" href="${pageContext.request.contextPath}/offenderFlagCategory/remove.html?offenderFlagCategory=${offenderFlagCategory.id}">
					<span class="linkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span>
				</a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>