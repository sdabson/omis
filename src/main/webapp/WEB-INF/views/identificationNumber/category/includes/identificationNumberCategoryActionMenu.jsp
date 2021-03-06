<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle" />
<fmt:bundle basename="omis.identificationnumber.msgs.identificationNumberCategory">
	<ul>
		<sec:authorize access="hasRole('IDENTIFICATION_NUMBER_CATEGORY_LIST') or hasRole('ADMIN')">
			<li>
				<a class="listLink" href="${pageContext.request.contextPath}/identificationNumber/category/list.html"><span class="visibleLinkLabel"><fmt:message key="listCategoriesLink"/></span></a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>