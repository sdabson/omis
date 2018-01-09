<!-- 
 - Author: Yidong Li
 - Version: 0.1.0 (Sept 6, 2016)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:bundle basename="omis.workassignment.msgs.workAssignment">
	<ul>
		<sec:authorize access="hasRole('WORK_ASSIGNMENT_CREATE') or hasRole('ADMIN') or hasRole('WORK_ASSIGNMENT_EDIT')">
			<li>
				<a class="createLink" id="addWorkAssignmentNoteItemLink" href="${pageContext.request.contextPath}/workAssignment/addWorkAssignmentNoteItem.html">
					<span class="visibleLinkLabel">
						<fmt:message key="addNoteLabel"/>
					</span>
				</a>
			</li>
		</sec:authorize>
	</ul>
</fmt:bundle>