<!-- 
 - Author: Sheronda Vaughn
 - Author: Yidong Li
 - Author: Sierra Haynes
 - Version: 0.1.0 (Sept 21, 2018)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.staff.msgs.staff">
	<ul>
	<sec:authorize access="hasRole('STAFF_ASSIGNMENT_VIEW') or hasRole('ADMIN')">
		<li class="taskLinkItem">
			<a class="viewEditLink" href="${pageContext.request.contextPath}/staffAssignment/edit.html?staffAssignment=${staffAssignment.id}">
				<fmt:message key="viewEditStaffLabel"/>
			</a>
		</li>	
	</sec:authorize>
	<sec:authorize access="hasRole('STAFF_ASSIGNMENT_REMOVE') or hasRole('ADMIN')">
		<li class="taskLinkItem">
			<a class="removeLink" href="${pageContext.request.contextPath}/staffAssignment/remove.html?staffAssignment=${staffAssignment.id}">
				<fmt:message key="StaffAssignmentRemoveLabel"/>
			</a>
		</li>	
	</sec:authorize>
	<sec:authorize access="hasRole('STAFF_ASSIGNMENT_VIEW') or hasRole('ADMIN')">
		<c:if test="${not empty staffAssignment}">
			<li>
				<a href="${pageContext.request.contextPath}/staffSearch/staffAssignmentDetailsReport.html?staffAssignment=${staffAssignment.id}&reportFormat=PDF" class="newTab printLink"><fmt:message key="staffAssignmentDetailsReportLinkLabel"/></a>
			</li>
		</c:if>
	</sec:authorize>
	<li>
		<a class="helpLink">
			<span class="visibleLinkLabel">
				<fmt:message key="searchHelpLabel"/>
			</span>
		</a>
	</li>	
</ul>
</fmt:bundle>