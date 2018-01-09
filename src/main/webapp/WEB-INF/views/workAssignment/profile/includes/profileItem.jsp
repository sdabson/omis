<%--
 - Profile item for work assignments.
 -
 - Author: Stephen Abson
 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:bundle basename="omis.workassignment.msgs.workAssignment">
  	<div class="profileItem">
  			<a href="${pageContext.request.contextPath}/workAssignment/list.html?offender=${offenderSummary.id}">
  				<span>
    				<fmt:message key="workAssignmentNameAndCountLabel">
    					<fmt:param value="${workAssignmentCount}"/>
    				</fmt:message>
    			</span>
    		</a>
   	</div>
</fmt:bundle>