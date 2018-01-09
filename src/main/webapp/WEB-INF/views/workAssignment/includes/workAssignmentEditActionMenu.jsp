<!-- 
 - Author: Yidong Li
 - Version: 0.1.0 (Aug 30, 2016)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.workassignment.msgs.workAssignment">
	<ul>
		<sec:authorize access="hasRole('WORK_ASSIGNMENT_LIST') or hasRole('ADMIN')">
			<li>
				<a class="listLink" id="workAssignmentEditActionMenuLink" href="${pageContext.request.contextPath}/workAssignment/list.html?offender=${offender.id}">
					<span class="visibleLinkLabel">
						<fmt:message key="listWorkAssignmentLabel"/>
					</span>
				</a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>