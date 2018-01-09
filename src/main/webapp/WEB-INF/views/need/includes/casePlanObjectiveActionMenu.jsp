<!-- 
 - Author: Joel Norris
 - Version: 0.1.0 (July 15, 2015)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.need.msgs.need">
	<ul>
		<sec:authorize access="hasRole('NEED_MODULE') or hasRole('ADMIN')">
			<li>
				<a class="createLink" href="${pageContext.request.contextPath}/need/casePlanObjective/list.html?offender=${offender.id}"><span class="visibleLinkLabel"><fmt:message key="casePlanObjectiveListLabel"/></span></a>
			</li>
		</sec:authorize>
		<li></li>
		<li></li>
	</ul>
</fmt:bundle>