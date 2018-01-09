<%--
  - Action menu for screen to change supervisory organization.
  -
  - Author: Stephen Abson
  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.placement.msgs.placement" var="placementBundle"/>
<ul>
    <sec:authorize access="hasRole('PLACEMENT_PROFILE_VIEW') or hasRole('ADMIN')">
    	<li class="moduleLink">
    		<a href="${pageContext.request.contextPath}/placement/home.html?offender=${offender.id}">
    			<fmt:message key="placementLink" bundle="${placementBundle}"/>
    		</a>
    	</li>
    </sec:authorize>
</ul>