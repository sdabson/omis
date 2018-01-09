<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.offender.msgs.offenderHeader" var="offenderHeader"/>
<fmt:setBundle basename="omis.msgs.common" var="commonBundle"/>
<fmt:bundle basename="omis.commitstatus.msgs.commitStatus">
	<ul>
		<sec:authorize access="hasRole('COMMIT_STATUS_EDIT') or hasRole('ADMIN')">
			<li>
				<a class="viewEditLink" href="${pageContext.request.contextPath}/commitStatus/list.html?offender=${offender.id}"><span class="visibleLinkLabel"><fmt:message key="listCommitStatusesLink" /></span></a>
			</li>
	 	</sec:authorize> 
	</ul>
</fmt:bundle>