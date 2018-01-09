<!-- 
 - Author: Sheronda Vaughn
 - Version: 0.1.0 (Dec 16, 2015)
 - Since: OMIS 3.0
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<fmt:setBundle basename="omis.offender.msgs.offender" var="offenderBundle"/>
<fmt:bundle basename="omis.offender.msgs.offenderSearch">
<ul>
	<sec:authorize access="hasRole('OFFENDER_CREATE') or hasRole('ADMIN')">
	<li class="taskLinkItem">
		<a class="createLink" href="${pageContext.request.contextPath}/offender/create.html">
			<fmt:message key="createOffenderHeader" bundle="${offenderBundle}"/>
		</a>
	</li>
	</sec:authorize>
	<li>
		<a class="printLink">
			<span class="visibleLinkLabel">
				<fmt:message key="printResultsLabel"/>
			</span>
		</a>
	</li>
	<li>
		<a class="helpLink">
			<span class="visibleLinkLabel">
				<fmt:message key="searchHelpLabel"/>
			</span>
		</a>
	</li>	
</ul>
</fmt:bundle>