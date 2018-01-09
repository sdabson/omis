<!-- 
 - Author: Annie Jacques
 - Version: 0.1.0 (Aug 3, 2015)
 - Since: OMIS 3.0
 -->
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.education.msgs.education">
	<ul>
		<sec:authorize access="hasRole('EDUCATION_CREATE') or hasRole('EDUCATION_EDIT') or hasRole('ADMIN')">
			<li>
				<a id="createAchievementItemLink" class="createLink" href="${pageContext.request.contextPath}/education/createAchievementItem.html?achievementItemIndex=${achievementItemIndex}"><span class="visibleLinkLabel"><fmt:message key="createAchievementLabel"/></span></a>
			</li>
		</sec:authorize>
		<li></li>
		<li></li>
	</ul>
</fmt:bundle>