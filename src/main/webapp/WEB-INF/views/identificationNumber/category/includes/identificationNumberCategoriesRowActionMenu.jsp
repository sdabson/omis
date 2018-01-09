<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.identificationnumber.msgs.identificationNumberCategory">
	<ul>
		<sec:authorize access="hasRole('IDENTIFICATION_NUMBER_CATEGORY_VIEW') or hasRole('ADMIN')">
			<li>
				<a class="viewEditLink" href="${pageContext.request.contextPath}/identificationNumber/category/edit.html?identificationNumberCategory=${identificationNumberCategory.id}"><span class="visibleLinkLabel"><fmt:message key="viewEditLink" bundle="${commonBundle}"/></span></a>
			</li>
		</sec:authorize>
		<sec:authorize access="hasRole('IDENTIFICATION_NUMBER_CATEGORY_REMOVE') or hasRole('ADMIN')">
			<li>
				<a class="removeLink" href="${pageContext.request.contextPath}/identificationNumber/category/remove.html?identificationNumberCategory=${identificationNumberCategory.id}"><span class="visibleLinkLabel"><fmt:message key="removeLink" bundle="${commonBundle}"/></span></a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>